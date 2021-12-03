package task2

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{FileIO, Framing, Sink}
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Utils {

  val as = ActorSystem()
  implicit val materializer = Materializer(as)


  def computeForwardAndDeep(filePath: String): Int = {
    val source = FileIO.fromPath(Paths.get(filePath))

    val flow = Framing
      .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 512, allowTruncation = true)
      .map(_.utf8String).map(el => {
      val splitted = el.split(" ")
      Direction(splitted(0),splitted(1).toInt)
    }
    )

    val sc =  source
      .via(flow)
      .runWith(Sink.seq)

    val list =  Await.result(sc, 10.seconds)

    val res = list.groupMapReduce(_.command)(_.value)(_ + _)

    val i = res.getOrElse("forward", 0)
    i * (res.getOrElse("down",0) - res.getOrElse("up",0)  )

  }


  def computeAndAim(filePath: String): Int = {
    val source = FileIO.fromPath(Paths.get(filePath))

    val flow = Framing
      .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 512, allowTruncation = true)
      .map(_.utf8String).map(el => {
      val splitted = el.split(" ")
      Direction(splitted(0),splitted(1).toInt)
    }
    )

    val sc =  source
      .via(flow)
      .runWith(Sink.seq)

    val list =  Await.result(sc, 10.seconds)

    calculateAim(0,0,0, list)

  }

  def calculateAim(horizontal: Int, aim: Int,deeper: Int, list: Seq[Direction]): Int = {
    var deep = deeper;
    var hor = horizontal;
    println(s"Deep is $deep and horizontal is $hor")
    if (list == Nil){
      deep * hor
    }else
    list.head match {
      case Direction("down", value) => {
        calculateAim(hor, aim + value,deep, list.tail)
      }
      case Direction("up", value) => {
        calculateAim(hor, aim - value,deep, list.tail)
      }
      case Direction("forward", value) => {
        deep += aim * value
        hor += value
        calculateAim(hor,aim, deep, list.tail)
      }
    }
  }
}

case class Direction(command: String, value: Int)

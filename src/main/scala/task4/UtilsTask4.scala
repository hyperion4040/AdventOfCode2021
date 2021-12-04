package task4

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{FileIO, Framing, Sink}
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object UtilsTask4 {


  val as = ActorSystem()
  implicit val materializer = Materializer(as)

  def bingo(filePath: String): Int = {
    val source = FileIO.fromPath(Paths.get(filePath))

    val flow1 = Framing
      .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 1024, allowTruncation = true)
      .map(_.utf8String).take(1).map{
      el => {
        val splitted = el.split(",")
        splitted.map(_.toInt).toSeq
      }
    }

    val flow2 = Framing
      .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 1024, allowTruncation = true)
      .map(_.utf8String).drop(2).map(el => {
      val splitted = el.trim.split("[ ]+")
      splitted.toSeq
    }
    ).filterNot(_.forall(_.isEmpty)).grouped(5).map{
      el => el.flatten.map(el => Pole(el.toInt))
    }

    val list = source.via(flow1).runWith(Sink.seq)

    val grid = source.via(flow2).runWith(Sink.seq)

    val numbers = Await.result(list, 10.seconds).flatten
    val result = Await.result(grid, 10.seconds)

    compute(numbers, result);

  }

  def compute( numbers: Seq[Int], list: Seq[Seq[Pole]] ): Int = {
    val re = markValueInList(numbers.head, list)
    var result = 0

    re.map{
      el => el.grouped(5).map{
        ek => if(ek.count(_.marked == true) == 5){
          val res = el.filter(_.marked == false)
          val rek = res.map(_.value).sum
          result = rek * numbers.head
        }
      }.toSeq
    }

    val res =  horizontalCheck(re, numbers.head)
    result += res

    result match {
      case 0 => compute(numbers.tail, re)
      case _ => result
    }
  }

  private def horizontalCheck(re: Seq[Seq[Pole]], number: Int): Int = {
    var result = 0
    (1 to 5).foreach{
      n =>
        result += filterColumn(re, n, number)
    }

    result

  }

  private def filterColumn(re: Seq[Seq[Pole]], n: Int, numbers: Int): Int = {
    var result = 0
  
    re.map {
      el => {
        val ma = el.zipWithIndex
          .filter { case (_, i) => (i + n) % 5 == 0 }
          .map { case (e, _) => e }

           if(ma.count(_.marked == true) == 5){
            val res = el.filter(_.marked == false)
            val rek = res.map(_.value).sum
            result = rek * numbers
          }
        }

    }

    result

  }

  private def markValueInList(number: Int, list: Seq[Seq[Pole]]) = {
    list.map {
      el =>
        el.map {
          es => if (es.value == number) Pole(es.value, marked = true) else Pole(es.value, es.marked)
        }
    }
  }
}


/*el => el.map{
es => if(es.count(_.marked == true) == 5){
val res = es.filter(_.marked == false)
val rek = res.map(_.value).sum
println(rek + " " + numbers.head)
result = rek * numbers.head
}
}*/

case class Pole(value: Int, marked: Boolean = false)

package task3

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{FileIO, Framing, Sink}
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object UtilsTask3 {

  val as = ActorSystem()
  implicit val materializer = Materializer(as)

  def computePowerConsumption(filePath: String): Int = {
    val source = FileIO.fromPath(Paths.get(filePath))

    val flow = Framing
      .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 512, allowTruncation = true)
      .map(_.utf8String)



    val sc =  source
      .via(flow)
      .runWith(Sink.seq)

    val list =  Await.result(sc, 10.seconds)


    val computedGamma = gamma(Seq.empty[Char], list)
    val computedEpsilon = epsilon(Seq.empty[Char], list)
    Integer.parseInt(computedGamma.mkString,2) * Integer.parseInt(computedEpsilon.mkString, 2)
  }


  def computeCo2(filePath: String): Int = {
    val source = FileIO.fromPath(Paths.get(filePath))

    val flow = Framing
      .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 512, allowTruncation = true)
      .map(_.utf8String)



    val sc =  source
      .via(flow)
      .runWith(Sink.seq)

    val list =  Await.result(sc, 10.seconds)

    val oxy = oxygenGen(0,list)
    val scru = scruGen(0,list)
    Integer.parseInt(oxy.mkString,2) * Integer.parseInt(scru.mkString, 2)
  }

  def oxygenGen(pos: Int, list: Seq[String]): String = list match {
    case seq if(seq.length == 1 || seq.head.length == pos) => list.head
    case seq => {
      val max = seq.map(el => el.charAt(pos)).groupBy(identity).maxBy(_._2.size)
      val min = seq.map(el => el.charAt(pos)).groupBy(identity).minBy(_._2.size)

      val maxOr = if(max._2.size == min._2.size) '1' else max._1
      oxygenGen(pos+1, list.filter(el => el.charAt(pos) == maxOr))
    }
  }

  def scruGen(pos: Int, list: Seq[String]): String = list match {
    case seq if(seq.length == 1 || seq.head.length == pos ) => list.head
    case seq => {
      val max = seq.map(el => el.charAt(pos)).groupBy(identity).maxBy(_._2.size)
      val min = seq.map(el => el.charAt(pos)).groupBy(identity).minBy(_._2.size)

      val minOr = if(max._2.size == min._2.size) '0' else min._1
      scruGen(pos+1, list.filter(el => el.charAt(pos) == minOr))
    }
  }

  def gamma(res: Seq[Char], list: Seq[String]):Seq[Char] = list match {
    case seq if(seq.map(el => el.length).max == 0) => res
    case seq => gamma(res :+ seq.map(el => el.head).groupBy(identity).maxBy(_._2.size)._1, seq.map(el => el.tail) )
  }

  def epsilon(res: Seq[Char], list: Seq[String]):Seq[Char] = list match {
    case seq if(seq.map(el => el.length).max == 0) => res
    case seq => epsilon(res :+ seq.map(el => el.head).groupBy(identity).minBy(_._2.size)._1, seq.map(el => el.tail) )
  }
}

package task1

import akka.actor.{Actor, ActorLogging}
import akka.http.scaladsl.Http
import akka.stream.scaladsl.{FileIO, Framing, Sink}
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class HelloActor extends  Actor with ActorLogging{

  implicit val system = context.system
  val http = Http(system)

   def receive: Receive = {
     case "start" => {
       val logFile = Paths.get("src/main/resources/task1/input.csv")

       val source = FileIO.fromPath(logFile)

       val flow = Framing
         .delimiter(ByteString(System.lineSeparator()), maximumFrameLength = 512, allowTruncation = true)
         .map(_.utf8String).map(_.toInt)

//       val sink = source.sliding(2).map{(x,y) => x < y }

//       source.via(flow).runWith(sink).map(println)(system.dispatcher)

       val sink2 = Sink.foreach(println)

//       source.via(flow).runWith(sink2)
      val sc =  source
         .via(flow)
         .runWith(Sink.seq)

       val list =  Await.result(sc, 10.seconds)

      val res = list.sliding(2).map{
        case Seq(x,y) => x < y
      }.count(_ == true)

      println(res)

     /*  val result = list.sliding(6).map{
         case Seq(x1,x2,x3,y1,y2,y3) => (x1 + x2 + x3) < (y1 + y2 + y3)
       }.count(_ == true)*/

       val result = list.sliding(3).map{
         case Seq(x,y,z) => x + y + z
       }.toList.sliding(2).map{
         case Seq(x,y) => x < y
       }.count(_ == true)

       println(result)
     }
   }
}

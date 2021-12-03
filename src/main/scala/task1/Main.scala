package task1

import akka.actor.{ActorSystem, Props}

object Main extends App{

  val system = ActorSystem("HelloSystem")

  val helloActor = system.actorOf(Props[HelloActor], name="helloActor")

 /* helloActor ! "hello"
  helloActor ! "oj"*/

  helloActor ! "start"
}

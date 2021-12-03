name := "AdventOfCode"

version := "0.1"

scalaVersion := "2.13.7"

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.7"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime,
  "org.scalactic" %% "scalactic" % "3.2.10",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test"

)

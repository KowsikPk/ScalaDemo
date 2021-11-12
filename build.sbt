name := "New"

version := "0.1"

scalaVersion := "2.13.7"

val AkkaVersion     = "2.6.8"
val AkkaHttpVersion = "10.2.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream"      % AkkaVersion,
  "com.typesafe.akka" %% "akka-http"        % AkkaHttpVersion
)

libraryDependencies += "com.themillhousegroup"      %% "scoup"                % "1.0.0"
libraryDependencies += "com.typesafe.akka"          %% "akka-http-spray-json" % AkkaHttpVersion
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging"        % "3.9.4"
libraryDependencies += "net.jockx"                   % "littleproxy"          % "1.1.3"
libraryDependencies += "com.github.ganskef"          % "littleproxy-mitm"     % "1.1.0"

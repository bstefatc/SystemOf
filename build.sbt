name := """SHSM"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)
libraryDependencies ++= Seq(
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.4.1"
)
libraryDependencies += "com.jcraft" % "jsch" % "0.1.53"
libraryDependencies += "com.google.code.gson" % "gson" % "2.3"
libraryDependencies += "org.mongodb.morphia" % "morphia" % "1.0.1"
libraryDependencies += "org.mongodb" % "mongodb-driver" % "3.2.0"
libraryDependencies += "org.elasticsearch" % "elasticsearch" % "2.3.2"
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "4.7"



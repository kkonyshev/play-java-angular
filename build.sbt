import com.typesafe.sbt.packager.docker.DockerPlugin
import com.typesafe.sbt.web.SbtWeb
import play.PlayJava

name := "advance-tweetmap-java8"

version := "1.0-SNAPSHOT"

val akka = "2.3.6"

libraryDependencies ++= Seq(
    javaWs,
    javaEbean,
    "org.webjars" % "bootstrap" % "3.0.0",
    "org.webjars" % "angularjs" % "1.2.16",
    "org.webjars" % "angular-leaflet-directive" % "0.7.6",
    "com.typesafe.akka" %% "akka-cluster" % akka,
    "com.typesafe.akka" %% "akka-contrib" % akka,
    "com.typesafe.akka" %% "akka-testkit" % akka % "test",
    "com.typesafe.akka" %% "akka-persistence-experimental" % akka exclude("org.iq80.leveldb","leveldb"),
    "org.iq80.leveldb"  %  "leveldb" % "0.7",
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb, DockerPlugin)

addCommandAlias("rb", "runMain backend.MainClusterManager backend 2560")

addCommandAlias("rb2", "runMain backend.MainClusterManager backend 2561")

addCommandAlias("sj", "runMain backend.journal.SharedJournalApp 2552 shared-journal")

import sbt._
import Keys._

object Build extends sbt.Build {

  lazy val project = Project(id = "root", base = file(".")) settings (
    scalaVersion := "2.10.1",
    libraryDependencies ++= Seq(
      "org.neo4j" % "neo4j-kernel" % "1.9.M05" classifier "tests",
      "org.scalatest" %% "scalatest" % "2.0.M5b"
    )
  )

}

import sbt._
import Keys._

object Build extends sbt.Build {

  lazy val project = Project(id = "root", base = file(".")) settings (
    scalaVersion := "2.10.0",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.0.M5b"
    )
  )

}

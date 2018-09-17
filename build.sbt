import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "kr.ac.kaist",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "xIS",
    libraryDependencies += scalaTest % Test,
		libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "2.1.0",
    libraryDependencies += "joda-time" % "joda-time" % "2.10",
		libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.12" 
  )

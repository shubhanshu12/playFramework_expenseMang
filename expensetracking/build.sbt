name := """expenseTracking"""
organization := "expTrack"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

libraryDependencies += "org.postgresql" % "postgresql" % "42.1.4"

libraryDependencies += javaJpa

libraryDependencies += "com.h2database" % "h2" % "1.4.197"

libraryDependencies += "org.hibernate" % "hibernate-core" % "5.1.10.Final"

PlayKeys.devSettings := Seq("play.server.http.idleTimeout" -> "infinite")


lazy val commonSettings = Seq(
  organization := "quedex",
  version := "0.1.0",
  scalaVersion := Common.scalaVersion,
  crossScalaVersions := Common.crossScalaVersions
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "arena-scala",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
      "org.scala-lang.modules" %% "scala-swing" % "1.0.2",
      "org.scala-lang.modules" %% "scala-async" % "0.9.5",
      "org.scala-lang.modules" %% "scala-java8-compat" % "0.7.0",
      "org.scala-lang" %% "scala-actors-migration" % "1.1.0",
      "org.scalatest" %% "scalatest" % "2.2.5" % Test,
      "com.typesafe.akka" %% "akka-actor" % "2.4.0",
      "com.typesafe.akka" %% "akka-testkit" % "2.4.0" % Test,
      "org.scalanlp" %% "breeze" % "0.11.2",
      "org.scalanlp" %% "breeze-natives" % "0.11.2",
      "org.scalanlp" %% "breeze-viz" % "0.11.2"
    )
  )


organization := "com.example.manything.ancientail"
name := """ancientail"""

version := "0.2.0-SNAPSHOT"

resolvers += Resolver.jcenterRepo

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "org.postgresql" % "postgresql" % "42.2.5",
      "org.typelevel" %% "cats-core" % "1.4.0",
      "org.typelevel" %% "cats-effect" % "1.0.0",
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.typesafe.play" %% "play-slick" % "3.0.3",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test",
      "com.example.manything" %% "roundelayout" % "0.2.0-SNAPSHOT"
    )
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value

scalaVersion := "2.12.6"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.manything.ancientail.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.manything.ancientail.binders._"

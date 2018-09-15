organization := "com.example.manything.ancientail"
name := """ancientail"""

version := "0.1.0-SNAPSHOT"

resolvers += Resolver.jcenterRepo

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test",
      "com.example.manything" %% "roundelayout" % "0.1.0-SNAPSHOT"
    )
  )
  .enablePlugins(PlayScala, ScalafmtPlugin)
  .disablePlugins(PlayLayoutPlugin)

PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value

scalaVersion := "2.12.6"

scalafmtOnCompile := true

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.manything.ancientail.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.manything.ancientail.binders._"

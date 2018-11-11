organization := "com.example.manything.ancientail"
name := """ancientail"""

version := "0.5.0-SNAPSHOT"

resolvers += Resolver.jcenterRepo

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      filters,
      // "org.postgresql" % "postgresql" % "42.2.5",
      "org.typelevel" %% "cats-core" % "1.4.0",
      "org.typelevel" %% "cats-effect" % "1.0.0",
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.github.tminglei" %% "slick-pg" % "0.16.3",
      "com.typesafe.play" %% "play-slick" % "3.0.3",
      "com.dripower" %% "play-circe" % "2610.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test",
      "org.mockito" % "mockito-core" % "2.23.0" % "test",
      "com.example.manything" %% "roundelayout" % "0.4.0"
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-Ypartial-unification"
    ),
    routesImport ++= Seq(
      "com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip._",
      "com.example.manything.ancientail.domain.slip._"
    )
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value

scalaVersion := "2.12.7"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.manything.ancientail.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.manything.ancientail.binders._"

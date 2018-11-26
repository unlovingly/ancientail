organization := "com.example.manything.ancientail"

scalaVersion := "2.12.7"

resolvers += Resolver.jcenterRepo

lazy val root = (project in file("."))
  .settings(
    name := """ancientail""",
    version := "0.5.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      filters,
      "org.typelevel"          %% "cats-core"          % "1.4.0",
      "org.typelevel"          %% "cats-effect"        % "1.0.0",
      "com.typesafe.slick"     %% "slick"              % "3.2.3",
      "com.github.tminglei"    %% "slick-pg"           % "0.16.3",
      "org.postgresql"         % "postgresql"          % "42.2.5",
      "com.typesafe.play"      %% "play-slick"         % "3.0.3",
      "com.dripower"           %% "play-circe"         % "2610.0",
      "io.circe"               %% "circe-java8"        % "0.10.1",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test",
      "org.mockito"            % "mockito-core"        % "2.23.0" % "test",
      "com.example.manything"  %% "roundelayout"       % "0.4.1-SNAPSHOT"
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

// TODO 外部ファイル化
lazy val flyway = (project in file("database"))
  .settings(
    name := "flyway",
    flywayUrl := s"jdbc:postgresql://$hostname:$port/$database?user=$username&password=$password",
    flywayLocations += "filesystem:database/migrations",
    libraryDependencies ++= Seq(
      "org.postgresql"         % "postgresql"          % "42.2.5",
    ),
  )
  .enablePlugins(FlywayPlugin)

lazy val username = sys.env.getOrElse("POSTGRES_USERNAME", "username")
lazy val password = sys.env.getOrElse("POSTGRES_PASSWORD", "p@ssw0rd")
lazy val hostname = sys.env.getOrElse("POSTGRES_HOSTNAME", "localhost")
lazy val database = sys.env.getOrElse("POSTGRES_DATABASE", "username")
lazy val port = sys.env.getOrElse("POSTGRES_PORT", "32768")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.manything.ancientail.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.manything.ancientail.binders._"

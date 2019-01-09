resolvers += Resolver.jcenterRepo

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      Seq(
        organization := "com.example.manything.ancientail",
        scalaVersion := "2.12.8",
        version := "0.6.2"
      )
    ),
    name := "ancientail",
    libraryDependencies ++= Seq(
      filters,
      "org.typelevel" %% "cats-core" % "1.5.0",
      "org.typelevel" %% "cats-effect" % "1.1.0",
      "org.typelevel" %% "cats-testkit" % "1.5.0" % "test",
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.github.tminglei" %% "slick-pg" % "0.17.0",
      "org.postgresql" % "postgresql" % "42.2.5",
      "com.typesafe.play" %% "play-slick" % "3.0.3",
      "com.dripower" %% "play-circe" % "2610.0",
      "io.circe" %% "circe-java8" % "0.11.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test",
    ),
    routesImport ++= Seq(
      "com.example.manything.ambientendre.domain.models.product.ProductId",
      "com.example.manything.ambientendre.domain.models.publisher.PublisherId",
      "com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.productIdBinder",
      "com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.publisherIdBinder",
      "com.example.manything.ancientail.domain.models.shop.PluCode",
      "com.example.manything.ancientail.domain.models.shop.ShopId",
      "com.example.manything.ancientail.domain.models.slip.SlipId",
      "com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop.pluCodeBinder",
      "com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop.shopIdBinder",
      "com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip.pathBinder",
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-Ypartial-unification"
    ),
    dockerBaseImage := "circleci/openjdk:8-jdk",
    dockerExposedPorts := Seq(9000),
    dockerUpdateLatest := true,
    javaOptions in Universal ++= Seq(
      // JVM memory tuning
      "-J-Xmx1024m",
      "-J-Xms512m",

      // "-Dplay.http.secret.key=fdstsafewa",

      // Since play uses separate pidfile we have to provide it with a proper path
      // name of the pid file must be play.pid
      // s"-Dpidfile.path=/var/run/${packageName.value}/play.pid",

      // alternative, you can remove the PID file
      // s"-Dpidfile.path=/dev/null",

      // Use separate configuration file for production environment
      // s"-Dconfig.file=/usr/share/${packageName.value}/conf/production.conf",

      // Use separate logger configuration file for production environment
      // s"-Dlogger.file=/usr/share/${packageName.value}/conf/production-logger.xml",
    )
  )
  .enablePlugins(DockerPlugin, JavaAppPackaging, PlayScala)
  .disablePlugins(PlayLayoutPlugin)

PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value

lazy val flyway = (project in file("database"))
  .settings(
    name := "flyway",
    flywayUrl := s"jdbc:postgresql://$hostname:$port/$database?user=$username&password=$password",
    flywayLocations += "filesystem:database/migrations",
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "42.2.5",
    ),
  )
  .enablePlugins(FlywayPlugin)

lazy val username = sys.env.getOrElse("POSTGRES_USER", "username")
lazy val password = sys.env.getOrElse("POSTGRES_PASSWORD", "p@ssw0rd")
lazy val hostname = sys.env.getOrElse("POSTGRES_HOSTNAME", "localhost")
lazy val database = sys.env.getOrElse("POSTGRES_DATABASE", "username")
lazy val port = sys.env.getOrElse("POSTGRES_PORT", "5432")

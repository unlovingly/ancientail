lazy val flyway = (project in file("migrations"))
  .settings(
    name := "flyway",
    scalaVersion := "2.12.7"
  )
  .enablePlugins(FlywayPlugin)

val username = sys.env.getOrElse("POSTGRES_USERNAME", "")
val password = sys.env.getOrElse("POSTGRES_PASSWORD", "")
val hostname = sys.env.getOrElse("POSTGRES_HOSTNAME", "localhost")
val database = sys.env.getOrElse("POSTGRES_DATABASE", "username")
val port = sys.env.getOrElse("POSTGRES_PORT", "32768")

flywayUrl := s"jdbc:postgresql://$hostname:$port/$database?user=$username&password=$password;shutdown=true"
flywayLocations += "migrations"

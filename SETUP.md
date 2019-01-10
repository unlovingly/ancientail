# Env

- APPLICATION_SECRET (from Play)
- DATABASE_HOSTNAME (POSTGRES_HOSTNAME)
- DATABASE_PORT (5432)
- DATABASE_NAME (pg)
- DATABASE_USERNAME (POSTGRES_USER)
- DATABASE_PASSWORD (POSTGRES_PASSWORD)

# Dev

1. docker-compose up -d
2. sbt
   1. project flyway
   2. flywayMigrate
3. curl http://localhost:9000

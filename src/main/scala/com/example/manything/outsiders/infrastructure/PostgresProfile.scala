package com.example.manything.outsiders.infrastructure

import com.github.tminglei.slickpg._
import slick.basic.Capability
import slick.jdbc.JdbcCapabilities

trait PostgresProfile
  extends ExPostgresProfile
  with PgDate2Support
  with PgRangeSupport {
  override protected def computeCapabilities: Set[Capability] =
    super.computeCapabilities + JdbcCapabilities.insertOrUpdate

  override val api: MyAPI.type = MyAPI

  object MyAPI extends API with DateTimeImplicits with RangeImplicits {}
}

object PostgresProfile extends PostgresProfile

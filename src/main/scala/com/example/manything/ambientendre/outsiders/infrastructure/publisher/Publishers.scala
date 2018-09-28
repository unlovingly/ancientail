package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import slick.jdbc.PostgresProfile.api._

class Publishers(tag: Tag) extends Table[Publisher](tag, "publishers") {
  def identity = column[PublisherId]("publisher_id", O.PrimaryKey)
  def name = column[String]("name")
  def * = (identity.?, name) <> (Publisher.tupled, Publisher.unapply)
}

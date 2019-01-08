package com.example.manything.ambientendre.outsiders.slick.publisher

import com.example.manything.ambientendre.domain.models.publisher.{ Publisher, PublisherId }
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Publishers(tag: Tag) extends Table[Publisher](tag, "publishers") {
  def identity =
    column[PublisherId]("publisher_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (identity.?, name) <> (Publisher.tupled, Publisher.unapply)
}

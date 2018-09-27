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

// https://stackoverflow.com/questions/39781193/slick-3-1-retrieving-subset-of-columns-as-a-case-class
// case class LiftedPublisher(id: Rep[PublisherId], name: Rep[String])

package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import slick.jdbc.PostgresProfile.api._
import com.example.manything.ambientendre.domain.publisher._

import scala.concurrent.Future

class PublisherRepositoryWithSlick(implicit val db: Database)
  extends PublisherRepository {
  override def retrieve: Future[Seq[Publisher]] = {
    val q = publishers
    val a = q.result

    db.run(a)
  }

  override def retrieve(id: PublisherId): Future[Publisher] = {
    val q = publishers.filter(_.identity === id)
    val a = q.result.head

    db.run(a)
  }

  override def store(entity: Publisher): Future[Publisher] = ???
}

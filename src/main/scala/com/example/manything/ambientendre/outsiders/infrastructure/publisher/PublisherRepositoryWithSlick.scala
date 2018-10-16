package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import slick.jdbc.PostgresProfile.api._
import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId,
  PublisherRepository
}

import scala.concurrent.{ExecutionContext, Future}

class PublisherRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends PublisherRepository[Future] {
  override def retrieve(): Future[Seq[Publisher]] = {
    val q = publishers.take(20)
    val a = q.result

    db.run(a)
  }

  override def retrieve(id: Seq[PublisherId]): Future[Seq[Publisher]] = {
    val q = for {
      p <- publishers if p.identity.inSet(id)
    } yield p
    val a = q.result

    db.run(a)
  }

  override def store(entity: Publisher): Future[Publisher] = {
    val q = (publishers returning publishers.map { _.identity }) += Publisher(
      entity.identity,
      entity.name)

    db.run(q)
      .map { id =>
        entity.copy(identity = Some(id))
      }
  }
}

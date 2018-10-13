package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import slick.jdbc.PostgresProfile.api._
import com.example.manything.ambientendre.domain.publisher.{
  PublisherId,
  PublisherRepository,
  Publisher => DPublisher
}

import scala.concurrent.{ExecutionContext, Future}

class PublisherRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends PublisherRepository[Future] {
  override def retrieve: Future[Seq[DPublisher]] = {
    val q = publishers.take(20)
    val a = q.result

    db.run(a).map {
      _.map { p =>
        DPublisher(p.identity, p.name)
      }
    }
  }

  override def retrieve(id: PublisherId): Future[DPublisher] = {
    val q = publishers.filter(_.identity === id)
    val a = q.result.head

    db.run(a)
      .map { p =>
        DPublisher(p.identity, p.name)
      }
  }

  override def store(entity: DPublisher): Future[DPublisher] = {
    val q = (publishers returning publishers.map { _.identity }) += Publisher(
      entity.identity,
      entity.name)

    db.run(q)
      .map { id =>
        entity.copy(identity = Some(id))
      }
  }
}

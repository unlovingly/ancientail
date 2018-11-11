package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

import slick.jdbc.PostgresProfile.api._

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.publisher._

class PublisherRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends PublisherRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityType]] = {
    val q = publishers.take(20)
    val a = q.result.asTry.map { _.toEither }

    db.run(a)
  }

  override def retrieve(
    id: Seq[Identifier]): EitherAppliedFuture[Seq[EntityType]] = {
    val q = for {
      p <- publishers if p.identity.inSet(id)
    } yield p
    val a = q.result.asTry.map { _.toEither }

    db.run(a)
  }

  override def store(entity: EntityType): EitherAppliedFuture[EntityType] = {
    val q = (publishers returning publishers.map { _.identity }) += Publisher(
      entity.identity,
      entity.name)
    val a = q.asTry
      .map {
        case Success(id) => Right(entity.copy(identity = Some(id)))
        case Failure(e) => Left(e)
      }

    db.run(a)
  }
}

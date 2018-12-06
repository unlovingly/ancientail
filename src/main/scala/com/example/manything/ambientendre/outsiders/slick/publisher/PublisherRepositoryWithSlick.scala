package com.example.manything.ambientendre.outsiders.slick.publisher

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.publisher._
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._
import com.example.manything.outsiders.slick.NotFoundException

class PublisherRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends PublisherRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = publishers.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.nonEmpty)
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = for {
      p <- publishers if p.identity === id
    } yield p
    val a = q.result.headOption.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.isDefined)
      .map { _.get }
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = (publishers returning publishers.map { _.identity }) += Publisher(
      entity.identity,
      entity.name)
    val a = q.asTry.map { _.toEither }

    EitherT(db.run(a)).map { identity =>
      entity.copy(identity = Some(identity))
    }
  }
}

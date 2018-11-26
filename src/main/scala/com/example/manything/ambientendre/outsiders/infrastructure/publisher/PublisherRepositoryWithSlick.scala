package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.publisher._
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class PublisherRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends PublisherRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    val q = publishers.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits._

    val q = for {
      p <- publishers if p.identity === id
    } yield p
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map { _.head }
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    val q = (publishers returning publishers.map { _.identity }) += Publisher(
      entity.identity,
      entity.name)
    val a = q.asTry.map { _.toEither }

    EitherT(db.run(a)).map { identity =>
      entity.copy(identity = Some(identity))
    }
  }
}

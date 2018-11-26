package com.example.manything.ambientendre.outsiders.infrastructure.product

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.product._
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class ProductRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends ProductRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    val q = products.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits._

    val q = for {
      p <- products if p.identity === id
    } yield p
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map { _.head }
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    val q = (products returning products.map { _.identity }) += Product(
      entity.identity,
      entity.name,
      entity.publisherId
    )
    val a = q.asTry.map { _.toEither }

    EitherT(db.run(a)).map { identity =>
      entity.copy(identity = Some(identity))
    }
  }
}

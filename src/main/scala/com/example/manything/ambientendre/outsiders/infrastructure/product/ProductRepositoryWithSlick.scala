package com.example.manything.ambientendre.outsiders.infrastructure.product

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

import slick.jdbc.PostgresProfile.api._

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product._

class ProductRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ProductRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityType]] = {
    val q = products
    val a = q.result.asTry.map { _.toEither }

    db.run(a)
  }

  override def retrieve(
    id: Seq[Identifier]): EitherAppliedFuture[Seq[EntityType]] = {
    val q = for {
      p <- products if p.identity.inSet(id)
    } yield p
    val a = q.result.asTry.map { _.toEither }

    db.run(a)
  }

  override def store(entity: EntityType): EitherAppliedFuture[EntityType] = {
    val q = (products returning products.map { _.identity }) += Product(
      entity.identity,
      entity.name,
      entity.publisherId
    )
    val a = q.asTry
      .map {
        case Success(id) => Right(entity.copy(identity = Some(id)))
        case Failure(e) => Left(e)
      }

    db.run(a)
  }
}

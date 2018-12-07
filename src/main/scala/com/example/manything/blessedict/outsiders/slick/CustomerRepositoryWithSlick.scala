package com.example.manything.blessedict.outsiders.slick

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.models.customer._
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class CustomerRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends CustomerRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = customers.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(new NoSuchElementException())(_.nonEmpty)
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = for {
      p <- customers if p.identity === id
    } yield p
    val a = q.result.headOption.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(new NoSuchElementException())(_.isDefined)
      .map { _.get }
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = (customers returning customers.map { _.identity }) += Customer(
      entity.identity,
      entity.name)
    val a = q.asTry.map { _.toEither }

    EitherT(db.run(a)).map { identity =>
      entity.copy(identity = Some(identity))
    }
  }
}

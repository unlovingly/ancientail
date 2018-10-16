package com.example.manything.ambientendre.outsiders.infrastructure.product

import com.example.manything.ambientendre.domain
import com.example.manything.ambientendre.domain.product.{
  Product,
  ProductId,
  ProductRepository
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class ProductRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ProductRepository[Future] {
  override def retrieve: Future[Seq[Product]] = {
    val q = products
    val a = q.result

    // FIXME
    db.run(a)
  }

  override def retrieve(id: Seq[ProductId]): Future[Seq[Product]] = {
    val q = for {
      p <- products if p.identity.inSet(id)
    } yield p
    val a = q.result

    db.run(a)
  }

  override def store(entity: Product): Future[Product] = {
    val q = (products returning products.map { _.identity }) += Product(
      entity.identity,
      entity.name,
      entity.publisherId
    )

    db.run(q)
      .map { id =>
        entity.copy(identity = Some(id))
      }
  }
}

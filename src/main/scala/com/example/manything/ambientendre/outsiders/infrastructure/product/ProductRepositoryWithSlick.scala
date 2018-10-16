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
  override def retrieve: Future[Seq[domain.product.Product]] = {
    val q = products
    val a = q.result

    // FIXME
    db.run(a).map { // Future
      _.map { product =>
        Product(product.identity, product.name, product.publisherId)
      }
    }
  }

  override def retrieve(id: ProductId): Future[domain.product.Product] = {
    val q = products
    val a = q.result.head

    db.run(a)
      .map { product =>
        Product(product.identity, product.name, product.publisherId)
      }
  }

  override def store(
    entity: domain.product.Product): Future[domain.product.Product] = {
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

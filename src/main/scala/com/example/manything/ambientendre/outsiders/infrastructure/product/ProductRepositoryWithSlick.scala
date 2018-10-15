package com.example.manything.ambientendre.outsiders.infrastructure.product

import com.example.manything.ambientendre.domain
import com.example.manything.ambientendre.domain.product.ProductRepository
import com.example.manything.ambientendre.domain.publisher.{
  Publisher => DPublisher
}
import com.example.manything.ambientendre.domain.product.{
  ProductId,
  ProductRepository,
  Product => DProduct
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class ProductRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ProductRepository[Future] {
  override def retrieve: Future[Seq[domain.product.Product]] = {
    import com.example.manything.ambientendre.outsiders.infrastructure.publisher._

    val q = for {
      publisher <- publishers
      product <- products if product.publisherId === publisher.identity
    } yield (product, publisher)
    val a = q.result

    // FIXME
    db.run(a).map { // Future
      _.map {
        case (product, publisher) => // Seq
          DProduct(
            product.identity,
            product.name,
            DPublisher(publisher.identity, publisher.name)
          )
      }
    }
  }

  override def retrieve(id: ProductId): Future[domain.product.Product] = {
    import com.example.manything.ambientendre.outsiders.infrastructure.publisher._

    val q = for {
      publisher <- publishers
      product <- products if product.publisherId === publisher.identity
    } yield (product, publisher)
    val a = q.result.head

    db.run(a)
      .map {
        case (product, publisher) =>
          DProduct(product.identity,
                   product.name,
                   DPublisher(publisher.identity, publisher.name))
      }
  }

  override def store(
    entity: domain.product.Product): Future[domain.product.Product] = {
    val q = (products returning products.map { _.identity }) += Product(
      entity.identity,
      entity.name,
      entity.publisher.identity.get
    )

    db.run(q)
      .map { id =>
        entity.copy(identity = Some(id))
      }
  }
}

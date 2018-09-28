package com.example.manything.ancientail.outsiders.infrastructure.product

import slick.jdbc.PostgresProfile.api._
import com.example.manything.ancientail.domain.product._

import scala.concurrent.{ExecutionContext, Future}

class ProductRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ProductRepository[Future] {
  override def retrieve: Future[Seq[Product]] = {
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
          Product(
            product.identity,
            product.name,
            publisher.identity.get
          )
      }
    }
  }
  override def retrieve(id: ProductId): Future[Product] =
    ???
  override def store(entity: Product): Future[Product] = ???
}

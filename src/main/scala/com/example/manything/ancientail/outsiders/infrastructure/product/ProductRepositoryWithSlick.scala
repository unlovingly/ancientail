package com.example.manything.ancientail.outsiders.infrastructure.product

import com.example.manything.ancientail.domain.product._

import scala.concurrent.Future

class ProductRepositoryWithSlick extends ProductRepository {
  override def retrieve: Future[Seq[Product]] = ???

  override def retrieve(id: ProductId): Future[Product] =
    ???

  override def store(entity: Product): Future[Product] = ???
}

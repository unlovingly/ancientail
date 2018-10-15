package com.example.manything.ancientail.usecases.product

import com.example.manything.ancientail.domain.product.Product
import scala.concurrent.Future

trait CreateProduct { this: ProductUseCases =>
  def create(p: Product): Future[Product] = products.store(p)
}

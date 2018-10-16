package com.example.manything.ambientendre.usecases.product
import com.example.manything.ambientendre.domain.product.Product

import scala.concurrent.Future

trait CreateProduct { this: ProductUseCases =>
  def create(p: Product): Future[Product] = products.store(p)
}

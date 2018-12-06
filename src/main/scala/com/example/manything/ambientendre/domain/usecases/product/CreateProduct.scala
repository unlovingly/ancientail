package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.product.Product

trait CreateProduct { this: ProductUseCases =>
  // TODO: トランザクション
  def create(p: Product): EitherTFuture[Product] = products.store(p)
}

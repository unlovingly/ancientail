package com.example.manything.ambientendre.usecases.product

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product.Product

trait CreateProduct { this: ProductUseCases =>
  // TODO: トランザクション
  def create(p: Product): EitherAppliedFuture[Product] = products.store(p)
}

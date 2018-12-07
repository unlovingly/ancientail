package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.ambientendre.domain.models.product.Product

trait CreateProduct[A[_]] { this: ProductUseCases[A] =>
  // TODO: トランザクション
  def create(p: Product): A[Product] = products.store(p)
}

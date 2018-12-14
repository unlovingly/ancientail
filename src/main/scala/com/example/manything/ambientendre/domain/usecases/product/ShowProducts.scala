package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.ambientendre.domain.models.product.{
  Product,
  ProductId
}

trait ShowProducts[A[_]] {
  this: ProductUseCases[A] =>
  def retrieve(): A[Seq[Product]] = products.retrieve()
  def retrieve(id: Seq[ProductId]): A[Seq[Product]] = products.retrieve(id)
}

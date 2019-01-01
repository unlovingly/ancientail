package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.ambientendre.domain.models.product._

class ProductUseCases[A[_]](val products: ProductRepository[A])
    extends CreateProduct[A]
    with ShowProducts[A] {
  override def create(p: Product): A[Product] = products.store(p)
  override def retrieve(): A[Seq[Product]] = products.retrieve()
  override def retrieve(id: Seq[ProductId]): A[Seq[Product]] =
    products.retrieve(id)
}

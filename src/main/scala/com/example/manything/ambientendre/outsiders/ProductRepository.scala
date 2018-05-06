package com.example.manything.ambientendre.outsiders

import com.example.manything.ambientendre.domain
import com.example.manything.ambientendre.domain.ProductIdentifier
import com.example.manything.roundelayout.Identifiability

// 実際のアレ
class ProductRepository extends domain.ProductRepository {
  override def retrieve(id: Identifiability[ProductIdentifier]): domain.Product = ???

  override def store(entity: domain.Product): domain.Product = ???
}

trait MixinProductRepository {
  val productRepository: ProductRepository = new ProductRepository
}

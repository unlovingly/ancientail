package com.example.manything.ambientendre.usecases

import com.example.manything.ambientendre.domain.ProductRepository

trait UsesProductRepository {
  val productRepository: ProductRepository
}

package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.ambientendre.domain.models.product.ProductRepository

class ProductUseCases[A[_]](val products: ProductRepository[A])
  extends CreateProduct[A]
  with ShowProducts[A]

package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.product.ProductRepository

class ProductUseCases(val products: ProductRepository[EitherTFuture])
  extends CreateProduct
  with ListingProducts

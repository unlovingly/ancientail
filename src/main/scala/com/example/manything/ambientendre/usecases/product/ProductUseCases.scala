package com.example.manything.ambientendre.usecases.product

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.product.ProductRepository

class ProductUseCases(val products: ProductRepository[EitherTFuture])
  extends CreateProduct
  with ListingProducts

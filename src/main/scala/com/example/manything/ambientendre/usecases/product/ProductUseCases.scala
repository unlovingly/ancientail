package com.example.manything.ambientendre.usecases.product

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product.ProductRepository

class ProductUseCases(
  implicit val products: ProductRepository[EitherAppliedFuture])
  extends CreateProduct
  with ListingProducts

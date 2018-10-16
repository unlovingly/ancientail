package com.example.manything.ambientendre.usecases.product

import com.example.manything.ambientendre.domain.product.ProductRepository

import scala.concurrent.Future

class ProductUseCases(implicit val products: ProductRepository[Future])
  extends CreateProduct
  with ListingProducts

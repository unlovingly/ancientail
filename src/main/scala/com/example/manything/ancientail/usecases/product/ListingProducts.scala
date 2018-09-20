package com.example.manything.ancientail.usecases.product

import com.example.manything.ancientail.domain.product.{
  Product,
  ProductRepository
}
import com.example.manything.roundelayout.usecase.UseCase

import scala.concurrent.Future

class ListingProducts(products: ProductRepository)
  extends UseCase[Seq[Product]] {
  def realize(): Future[Seq[Product]] = products.retrieve
}

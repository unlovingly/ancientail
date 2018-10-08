package com.example.manything.ancientail.usecases.product

import com.example.manything.ancientail.domain.product.{
  Product,
  ProductRepository
}
import com.example.manything.roundelayout.usecase.UseCase

import scala.concurrent.Future

class ListingProducts(products: ProductRepository[Future])
  extends UseCase[Seq[Product], Seq[Product]] {
  def realize(p: Seq[Product]): Future[Seq[Product]] = products.retrieve
}

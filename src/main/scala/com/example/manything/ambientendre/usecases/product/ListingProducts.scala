package com.example.manything.ambientendre.usecases.product
import com.example.manything.ambientendre.domain.product.Product

import scala.concurrent.Future

trait ListingProducts {
  this: ProductUseCases =>
  def list(): Future[Seq[Product]] = products.retrieve
}

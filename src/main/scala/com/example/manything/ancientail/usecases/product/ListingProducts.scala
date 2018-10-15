package com.example.manything.ancientail.usecases.product

import com.example.manything.ancientail.domain.product._

import scala.concurrent.Future

trait ListingProducts {
  this: ProductUseCases =>
  def list(): Future[Seq[Product]] = products.retrieve
}

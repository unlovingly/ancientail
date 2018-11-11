package com.example.manything.ambientendre.usecases.product

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product.Product

trait ListingProducts {
  this: ProductUseCases =>
  def list(): EitherAppliedFuture[Seq[Product]] = products.retrieve()
}

package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.product.Product

trait ListingProducts {
  this: ProductUseCases =>
  def list(): EitherTFuture[Seq[Product]] = products.retrieve()
}

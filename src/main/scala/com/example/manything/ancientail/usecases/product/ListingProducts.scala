package com.example.manything.ancientail.usecases.product

import com.example.manything.ancientail.domain.product.{
  Product,
  ProductRepository
}
import com.example.manything.roundelayout.usecase.UseCase

class ListingProducts[C[_]](products: ProductRepository[C])
  extends UseCase[Seq[Product], C] {
  def realize(): C[Seq[Product]] = products.retrieve
}

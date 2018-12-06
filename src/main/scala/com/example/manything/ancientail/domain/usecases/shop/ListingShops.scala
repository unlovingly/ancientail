package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.shop.Shop

trait ListingShops { this: ShopUseCases =>
  def list(): EitherTFuture[Seq[Shop]] = shops.retrieve()
}

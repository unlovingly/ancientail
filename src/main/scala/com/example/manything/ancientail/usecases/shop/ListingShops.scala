package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.{Shop, ShopId}

trait ListingShops { this: ShopUseCases =>
  def list(): EitherTFuture[Seq[Shop]] = shops.retrieve()
}

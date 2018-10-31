package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.Shop

trait ListingShops { this: ShopUseCases =>
  def list(): EitherAppliedFuture[Seq[Shop]] = shops.retrieve
}

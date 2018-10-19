package com.example.manything.ancientail.usecases.shop

import com.example.manything.ancientail.domain.shop.Shop

import scala.concurrent.Future

trait ListingShops { this: ShopUseCases =>
  def list(): Future[Seq[Shop]] = shops.retrieve
}

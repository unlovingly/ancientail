package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.Shop

trait ListingStocks { this: ShopUseCases =>
  def retrieveWithStocks(q: String): EitherTFuture[Seq[Shop]] =
    shops.retrieveWithStocks(q)
}

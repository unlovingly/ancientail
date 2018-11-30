package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop._

trait ShowStocks { this: ShopUseCases =>
  def retrieve(shopId: ShopId, code: PluCode): EitherTFuture[Shop] = {
    shops.retrieveWithStocks(shopId, code)
  }
}

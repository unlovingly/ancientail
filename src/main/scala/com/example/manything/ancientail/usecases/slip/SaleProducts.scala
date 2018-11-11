package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._

/**
 * 詳細をみるときのユースケース
 */
trait SaleProducts { this: SlipUseCases =>
  def sell(shopId: ShopId): EitherTFuture[Seq[SlipBase]] = ???
}

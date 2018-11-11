package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveOneSlip { this: SlipUseCases =>
  def retrieve(shopId: ShopId, slipId: SlipId): EitherTFuture[SlipBase] =
    ???
}

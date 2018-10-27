package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.{Slip, SlipId}

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveProducts { this: SlipUseCases =>
  def retrieve(shopId: ShopId,
               slipId: SlipId): EitherAppliedFuture[Seq[Slip]] = {
    slips.retrieve(Seq(slipId))
  }
}

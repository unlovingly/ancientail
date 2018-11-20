package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveOneSlip { this: SlipUseCases =>
  def retrieve(shopId: ShopId, slipId: SlipId): EitherTFuture[PurchaseSlip] =
    purchaseSlips.retrieve(slipId)
}

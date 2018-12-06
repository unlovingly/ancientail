package com.example.manything.ancientail.domain.usecases.slip

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveOneSlip[A[_]] { this: SlipUseCases[A] =>
  def retrieve(shopId: ShopId, slipId: SlipId): A[EntityType] = {
    slips.retrieve(slipId)
  }
}

package com.example.manything.ancientail.domain.usecases.slip

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId

/**
 * 詳細をみるときのユースケース
 */
trait ShowSlips[A[_]] { this: SlipUseCases[A] =>
  def retrieve(): A[Seq[EntityType]] = {
    slips.retrieve()
  }

  def retrieve(shopId: ShopId, slipId: SlipId): A[EntityType] = {
    slips.retrieve(slipId)
  }
}

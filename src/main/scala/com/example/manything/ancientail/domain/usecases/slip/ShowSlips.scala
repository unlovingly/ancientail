package com.example.manything.ancientail.domain.usecases.slip

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId

trait ShowSlips[A[_]] { this: SlipUseCases[A] =>

  /**
   * 登録されている伝票を取得する
   */
  def retrieve(): A[Seq[EntityType]] = {
    slips.retrieve()
  }

  /**
   * 登録されている伝票から指定された伝票を取得する
   */
  def retrieve(shopId: ShopId, slipId: SlipId): A[EntityType] = {
    slips.retrieve(slipId)
  }
}

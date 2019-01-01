package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop.{ Shop, ShopId }

trait ShowShops[A[_]] {

  /**
   * 登録されている店舗を取得する
   */
  def retrieve(): A[Seq[Shop]]

  /**
   * 登録されている店舗から指定された店舗を取得する
   */
  def retrieve(shopId: ShopId): A[Shop]
}

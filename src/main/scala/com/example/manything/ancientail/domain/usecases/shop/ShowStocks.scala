package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop._

trait ShowStocks[A[_]] {

  /**
   * q を製品型番に含む在庫を取得する
   */
  def retrieveWithStocksBy(q: String): A[Seq[Shop]]

  /**
   * 指定された店舗の在庫を取得する
   */
  def retrieveWithStocksBy(shopId: ShopId): A[Shop]

  /**
   * 指定された在庫を取得する
   */
  def retrieveWithStocksBy(shopId: ShopId, code: PluCode): A[Shop]
}

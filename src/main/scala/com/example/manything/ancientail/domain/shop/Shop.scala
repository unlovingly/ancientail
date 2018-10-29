package com.example.manything.ancientail.domain.shop

import com.example.manything.ancientail.domain.slip.Slip
import com.example.manything.roundelayout.domain.Entity

case class Shop(
  identity: Option[ShopId] = None,
  name: String,
  stocks: Seq[Stock]
) extends Entity[ShopId] {

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   *
   * 更新するのは slip, stock
   * @param slip
   */
  def storing(slip: Slip): Shop = {
    import cats.implicits._

    val newStocks: Seq[Stock] = slip.items.map { s =>
      Stock(shopId = slip.receiverId,
            productId = s.productId,
            amount = s.amount,
            price = s.price)
    }

    val result: Seq[Stock] = newStocks.toList |+| stocks.toList

    this.copy(stocks = result)
  }
}

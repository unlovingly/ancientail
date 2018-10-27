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
    // TODO: amount
    val s = stocks ++ slip.items.map { i =>
      Stock(shopId = slip.receiverId,
            productId = i.productId,
            amount = i.amount,
            price = i.price)
    }

    this.copy(stocks = s)
  }
}

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
   */
  def storing(slip: Slip): Shop = {
    import cats.implicits._

    val newStocks: Seq[Stock] = slip.items.map { s =>
      Stock(
        pluCode =
          PluCode.generate(v = slip.receiverId, a = s.productId, l = s.price),
        shopId = slip.receiverId,
        productId = s.productId,
        amount = s.amount,
        price = s.price)
    }

    val result: Seq[Stock] = (newStocks ++ stocks)
      .filter(s => s.shopId === slip.receiverId)
      .groupBy(_.pluCode)
      .mapValues(_.reduce((x, y) => x |+| y))
      .values
      .toSeq

    this.copy(stocks = result)
  }
}

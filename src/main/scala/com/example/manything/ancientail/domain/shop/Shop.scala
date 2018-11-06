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
  def storing[S <: Slip[_]](slip: S): Shop = {
    val newStocks: Seq[Stock] = slip.items.map { s =>
      Stock(pluCode =
              PluCode.generate(v = identity.get, a = s.productId, l = s.price),
            shopId = identity.get,
            productId = s.productId,
            amount = s.amount,
            price = s.price)
    }

    updateStocks(newStocks)
  }

  def inbound[S <: Slip[_]](slip: S): Shop = {
    updateStocks(Seq.empty)
  }

  def outbound[S <: Slip[_]](slip: S): Shop = {
    updateStocks(Seq.empty)
  }

  private def updateStocks(newStocks: Seq[Stock]): Shop = {
    import cats.implicits._

    val result: Seq[Stock] = newStocks
      .groupBy(_.pluCode)
      .mapValues(_.reduce((x, y) => x |+| y))
      .values
      .toSeq

    this.copy(stocks = result)
  }
}

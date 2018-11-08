package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.ancientail.domain.slip._
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class Shop(
  override val identity: Option[ShopId] = None,
  name: String,
  stocks: Seq[Stock]
) extends Entity {
  override type Identifier = ShopId

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def storing(slip: PurchaseSlip): Shop = inbound(slip)

  def inbound(slip: SlipBase): Shop = {
    import cats.implicits._

    val newStocks: Seq[Stock] = convertFrom(slip.items)

    val result: Seq[Stock] = (newStocks ++ stocks)
      .groupBy(_.pluCode)
      .mapValues(_.reduce((x, y) => x |+| y))
      .values
      .toSeq

    this.copy(stocks = result)
  }

  def outbound(slip: ExchangeSlip): Shop = {
    val newStocks: Seq[Stock] = convertFrom(slip.items)

    val result: Seq[Stock] = (stocks ++ newStocks)
      .groupBy(_.pluCode)
      .mapValues(_.reduce((x, y) => x - y))
      .values
      .toSeq

    this.copy(stocks = result)
  }

  private def convertFrom(items: Seq[SlipItem]): Seq[Stock] = {
    items.map { s =>
      Stock(pluCode = PluCode.generate(v = s.productId, a = s.price),
            shopId = identity.get,
            productId = s.productId,
            amount = s.amount,
            price = s.price)
    }
  }
}

case class ShopId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = Shop
}

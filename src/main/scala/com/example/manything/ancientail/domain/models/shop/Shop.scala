package com.example.manything.ancientail.domain.models.shop

import java.util.UUID

import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip
import com.example.manything.ancientail.domain.models.slip.{Slip, SlipItem}
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class Shop(
  override val identity: Option[ShopId] = None,
  name: String,
  stocks: Seq[Stock]
) extends Entity {
  override type Identifier = ShopId

  /**
   * 入庫処理
   */
  def inbound(slip: Slip): Shop = {
    import cats.syntax.monoid.catsSyntaxSemigroup

    val newStocks = convertFrom(slip.items)

    val result = (newStocks ++ stocks)
      .groupBy(_.pluCode)
      .values
      .map(_.reduce((x, y) => x |+| y))
      .toSeq

    this.copy(stocks = result)
  }

  /**
   * 出庫処理
   */
  def outbound(slip: Slip): Shop = {
    val newStocks = convertFrom(slip.items)

    // newStocks に含まれるキーは stocks にもあると保証しておかねばならない
    val ka = newStocks.map(_.pluCode).toSet
    val kb = stocks.map(_.pluCode).toSet

    require(ka subsetOf kb)

    val result = (stocks ++ newStocks)
      .groupBy(_.pluCode)
      .values
      .map(_.reduce((x, y) => x - y))
      .toSeq

    this.copy(stocks = result)
  }

  def sell(slip: SalesSlip): Shop = outbound(slip)

  /**
   * 仕入れ処理
   *
   * システム上では入庫処理に相当する
   */
  def storing(slip: PurchaseSlip): Shop = inbound(slip)

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

case class ShopId(override val value: UUID) extends Identifiability[Shop, UUID]

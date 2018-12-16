package com.example.manything.ancientail.domain.models.shop

import java.util.UUID

import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

/**
 * 店舗
 * Publisher から製品を仕入れ (Storing) 商品在庫 (Stock) として販売する。
 * @param identity Option[ShopId]
 * @param name String 店舗名
 * @param stocks Seq[Stock] 在庫
 */
case class Shop(
  override val identity: Option[ShopId] = None,
  name: String,
  stocks: Seq[Stock]
) extends Entity {
  override type Identifier = ShopId

  /**
   * 入庫処理
   */
  def inbound(ss: Seq[Stock]): Shop = {
    val result = (ss ++ stocks)
      .groupBy(_.pluCode)
      .values
      .map(_.reduce((x, y) => x + y))
      .toSeq

    this.copy(stocks = result)
  }

  /**
   * 出庫処理
   */
  def outbound(ss: Seq[Stock]): Shop = {
    // newStocks に含まれるキーは stocks にもあると保証しておかねばならない
    val ka = ss.map(_.pluCode).toSet
    val kb = stocks.map(_.pluCode).toSet

    require(ka subsetOf kb)

    val result = (stocks ++ ss)
      .groupBy(_.pluCode)
      .values
      .map(_.reduce((x, y) => x - y))
      .toSeq

    this.copy(stocks = result)
  }

  def sell(slip: SalesSlip): Shop = ???

  /**
   * 仕入れ処理
   *
   * システム上では入庫処理に相当する
   */
  def storing(slip: PurchaseSlip): Shop = {
    val ss = slip.items.map { i =>
      Stock(pluCode = PluCode.generate(v = i.productId, a = i.price),
            shopId = identity.get,
            productId = i.productId,
            amount = i.amount,
            price = i.price)
    }

    inbound(ss)
  }
}

case class ShopId(override val value: UUID) extends Identifiability[Shop, UUID]

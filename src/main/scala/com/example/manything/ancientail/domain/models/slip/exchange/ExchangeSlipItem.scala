package com.example.manything.ancientail.domain.models.slip.exchange

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip._

/**
 * 伝票の列…
 * @param identity Option[SlipItemId]
 * @param productId ProductId
 * @param amount 数量
 * @param price 仕入れ値
 */
case class ExchangeSlipItem(
  override val identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price
) extends SlipItem

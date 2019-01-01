package com.example.manything.ancientail.domain.models.slip.sales

import com.example.manything.ancientail.domain.models.shop.PluCode
import com.example.manything.ancientail.domain.models.slip._

/**
 * 伝票の列…
 *
 * @param identity Option[SlipItemId]
 * @param pluCode PluCode
 * @param amount 数量
 * @param price 仕入れ値
 */
case class SalesSlipItem(
    override val identity: Option[SlipItemId] = None,
    pluCode: PluCode,
    amount: Amount,
    price: Price
) extends SlipItem

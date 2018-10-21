package com.example.manything.ancientail.domain.slip

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.roundelayout.domain.Entity

case class SlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price
) extends Entity[SlipItemId]

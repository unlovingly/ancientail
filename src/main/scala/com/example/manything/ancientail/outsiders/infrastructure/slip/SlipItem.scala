package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip._

case class SlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price,
  slipId: SlipId
)

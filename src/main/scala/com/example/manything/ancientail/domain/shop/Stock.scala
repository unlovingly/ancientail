package com.example.manything.ancientail.domain.shop

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip.{Amount, Price}

case class Stock(
  shopId: ShopId,
  productId: ProductId,
  amount: Amount, // TODO: ensuring(amount > 0)
  price: Price
)

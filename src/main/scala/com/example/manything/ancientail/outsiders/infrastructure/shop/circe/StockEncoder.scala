package com.example.manything.ancientail.outsiders.infrastructure.shop.circe

import com.example.manything.ancientail.domain.models.shop.Stock

trait StockEncoder {
  import io.circe._
  import io.circe.generic.semiauto._

  import com.example.manything.ambientendre.outsiders.infrastructure.product.circe.ProductCodec.productIdEncoder
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.PluCodeCodec.pluCodeEncoder
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.ShopCodec.shopIdEncoder

  implicit lazy val stockEncoder: Encoder[Stock] =
    deriveEncoder
}

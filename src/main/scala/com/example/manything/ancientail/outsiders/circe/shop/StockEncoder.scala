package com.example.manything.ancientail.outsiders.circe.shop

import com.example.manything.ancientail.domain.models.shop.Stock

trait StockEncoder {
  import io.circe._
  import io.circe.generic.semiauto._

  import com.example.manything.ambientendre.outsiders.infrastructure.product.circe.ProductCodec.productIdEncoder
  import com.example.manything.ancientail.outsiders.circe.shop.PluCodeCodec.pluCodeEncoder
  import com.example.manything.ancientail.outsiders.circe.shop.ShopCodec.shopIdEncoder

  implicit lazy val stockEncoder: Encoder[Stock] =
    deriveEncoder
}

package com.example.manything.ancientail.outsiders.infrastructure.shop.circe

import com.example.manything.ancientail.domain.shop.Stock

trait StockDecoder {
  import io.circe.Decoder

  import com.example.manything.ambientendre.outsiders.infrastructure.product.circe.ProductCodec.productIdDecoder
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.PluCodeCodec.pluCodeDecoder
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.ShopCodec.shopIdDecoder

  implicit lazy val stockDecoder: Decoder[Stock] =
    Decoder.forProduct5("pluCode", "shopId", "productId", "name", "price")(
      Stock.apply)
}

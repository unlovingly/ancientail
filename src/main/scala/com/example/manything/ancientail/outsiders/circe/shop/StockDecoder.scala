package com.example.manything.ancientail.outsiders.circe.shop

import com.example.manything.ancientail.domain.models.shop.Stock

trait StockDecoder {
  import io.circe.Decoder

  import com.example.manything.ambientendre.outsiders.circe.product.ProductCodec.productIdDecoder
  import com.example.manything.ancientail.outsiders.circe.shop.PluCodeCodec.pluCodeDecoder
  import com.example.manything.ancientail.outsiders.circe.shop.ShopCodec.shopIdDecoder

  implicit lazy val stockDecoder: Decoder[Stock] =
    Decoder.forProduct5("pluCode", "shopId", "productId", "amount", "price")(
      Stock.apply)
}

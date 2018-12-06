package com.example.manything.ancientail.outsiders.circe.shop

import com.example.manything.ancientail.domain.models.shop.{Shop, ShopId}

trait ShopEncoder {
  import io.circe.Encoder

  import com.example.manything.ancientail.outsiders.circe.shop.StockCodec.stockEncoder

  implicit lazy val shopIdEncoder: Encoder[ShopId] =
    Encoder.encodeString.contramap[ShopId](_.value.toString)

  implicit lazy val shopEncoder: Encoder[Shop] =
    Encoder.forProduct3("id", "name", "stocks")(s =>
      (s.identity, s.name, s.stocks))
}

package com.example.manything.ancientail.outsiders.infrastructure.shop.circe

import com.example.manything.ancientail.domain.shop.{Shop, ShopId}

trait ShopEncoder {
  import io.circe.Encoder

  implicit lazy val shopIdEncoder: Encoder[ShopId] =
    Encoder.encodeString.contramap[ShopId](_.value.toString)

  implicit lazy val shopEncoder: Encoder[Shop] =
    Encoder.forProduct2("id", "name")(s => (s.identity, s.name))
}

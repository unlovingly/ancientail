package com.example.manything.ancientail.outsiders.infrastructure.shop.circe

import java.util.UUID

import com.example.manything.ancientail.domain.shop.{Shop, ShopId}

trait ShopDecoder {
  import cats.syntax.either._

  import io.circe.Decoder

  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.StockCodec.stockDecoder

  implicit lazy val shopIdDecoder: Decoder[ShopId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(ShopId(UUID.fromString(str)))
        .leftMap(_ => "ShopId")
    }

  implicit lazy val shopDecoder: Decoder[Shop] =
    Decoder.forProduct3("identity", "name", "stocks")(Shop.apply)
}

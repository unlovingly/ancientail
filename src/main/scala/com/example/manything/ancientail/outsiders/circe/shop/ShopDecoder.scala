package com.example.manything.ancientail.outsiders.circe.shop

import java.util.UUID

import com.example.manything.ancientail.domain.models.shop.{ Shop, ShopId }

trait ShopDecoder {
  import cats.syntax.either.catsSyntaxEither
  import cats.syntax.either.catsSyntaxEitherObject

  import io.circe.Decoder

  import com.example.manything.ancientail.outsiders.circe.shop.StockCodec.stockDecoder

  implicit lazy val shopIdDecoder: Decoder[ShopId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(ShopId(UUID.fromString(str)))
        .leftMap(_ => "ShopId")
    }

  implicit lazy val shopDecoder: Decoder[Shop] =
    Decoder.forProduct3("identity", "name", "stocks")(Shop.apply)
}

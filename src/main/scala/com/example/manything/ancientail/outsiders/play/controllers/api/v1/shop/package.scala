package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ancientail.domain.shop._

package object shop {
  import cats.syntax.either._

  import io.circe.generic.semiauto._
  import io.circe.{Decoder, Encoder}

  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.decodeProductId
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.encodeProductId

  implicit lazy val stockEncoder: Encoder[Stock] =
    deriveEncoder

  implicit lazy val pluCodeEncoder: Encoder[PluCode] =
    Encoder.encodeString.contramap[PluCode](_.value.toString)

  implicit lazy val shopIdEncoder: Encoder[ShopId] =
    Encoder.encodeString.contramap[ShopId](_.value.toString)

  implicit lazy val shopIdDecoder: Decoder[ShopId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(ShopId(UUID.fromString(str)))
        .leftMap(_ => "ShopId")
    }

  implicit lazy val decodePluCode: Decoder[PluCode] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(PluCode(str))
        .leftMap(_ => "PluCode")
    }

  implicit lazy val stockDecoder: Decoder[Stock] =
    Decoder.forProduct5("pluCode", "shopId", "productId", "name", "price")(
      Stock.apply)

  implicit lazy val shopDecoder: Decoder[Shop] =
    Decoder.forProduct3("identity", "name", "stocks")(Shop.apply)
}

package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ancientail.domain.shop._
import com.example.manything.roundelayout.domain.Identifiability

package object shop {
  import cats.syntax.either._
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.decodeProductId
  import io.circe.{Decoder, Encoder}

  implicit val encodeShopId: Encoder[ShopId] =
    Encoder.encodeString.contramap[ShopId](_.value.toString)

  implicit val decodeShopId: Decoder[ShopId] = Decoder.decodeString.emap {
    str =>
      Either
        .catchNonFatal(Identifiability[UUID, Shop](UUID.fromString(str)))
        .leftMap(_ => "ShopId")
  }

  implicit val stockDecoder: Decoder[Stock] =
    Decoder.forProduct4("shopId", "productId", "name", "price")(Stock.apply)

  implicit val shopDecoder: Decoder[Shop] =
    Decoder.forProduct3("identity", "name", "stocks")(Shop.apply)
}

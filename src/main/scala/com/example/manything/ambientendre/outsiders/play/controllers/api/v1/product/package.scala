package com.example.manything.ambientendre.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ambientendre.domain.product.{Product, ProductId}
import com.example.manything.roundelayout.domain.Identifiability

package object product {
  import cats.syntax.either._
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.decodePublisherId
  import io.circe.{Decoder, Encoder}

  implicit val encodeProductId: Encoder[ProductId] =
    Encoder.encodeString.contramap[ProductId](_.value.toString)

  implicit val decodeProductId: Decoder[ProductId] = Decoder.decodeString.emap {
    str =>
      Either
        .catchNonFatal(Identifiability[UUID, Product](UUID.fromString(str)))
        .leftMap(_ => "ProductId")
  }

  implicit val productDecoder: Decoder[Product] =
    Decoder.forProduct3("identity", "name", "publisherId")(Product.apply)
}

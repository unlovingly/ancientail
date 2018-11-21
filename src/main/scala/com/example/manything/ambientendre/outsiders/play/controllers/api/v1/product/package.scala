package com.example.manything.ambientendre.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ambientendre.domain.product.{Product, ProductId}

package object product {
  import cats.syntax.either._

  import io.circe.{Decoder, Encoder}

  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.publisherIdDecoder
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.publisherIdEncoder

  implicit lazy val encodeProductId: Encoder[ProductId] =
    Encoder.encodeString.contramap[ProductId](_.value.toString)

  implicit lazy val decodeProductId: Decoder[ProductId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(ProductId(UUID.fromString(str)))
        .leftMap(_ => "ProductId")
    }

  implicit lazy val productDecoder: Decoder[Product] =
    Decoder.forProduct3("id", "name", "publisherId")(Product.apply)

  implicit lazy val productEncoder: Encoder[Product] =
    Encoder.forProduct3("id", "name", "publisherId")(p =>
      (p.identity, p.name, p.publisherId))
}

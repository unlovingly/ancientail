package com.example.manything.ambientendre.outsiders.infrastructure.product.circe

import java.util.UUID

import com.example.manything.ambientendre.domain.product.{Product, ProductId}

trait ProductDecoder {
  import cats.syntax.either._

  import io.circe.Decoder

  import com.example.manything.ambientendre.outsiders.infrastructure.publisher.circe.PublisherCodec.publisherIdDecoder

  implicit lazy val productIdDecoder: Decoder[ProductId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(ProductId(UUID.fromString(str)))
        .leftMap(_ => "ProductId")
    }

  implicit lazy val productDecoder: Decoder[Product] =
    Decoder.forProduct3("id", "name", "publisherId")(Product.apply)
}

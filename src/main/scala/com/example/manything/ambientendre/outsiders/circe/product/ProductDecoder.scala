package com.example.manything.ambientendre.outsiders.circe.product

import java.util.UUID

import com.example.manything.ambientendre.domain.models.product.{ Product, ProductId }

trait ProductDecoder {
  import cats.syntax.either._

  import io.circe.Decoder

  import com.example.manything.ambientendre.outsiders.circe.publisher.PublisherCodec.publisherIdDecoder

  implicit lazy val productIdDecoder: Decoder[ProductId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(ProductId(UUID.fromString(str)))
        .leftMap(_ => "ProductId")
    }

  implicit lazy val productDecoder: Decoder[Product] =
    Decoder.forProduct3("identity", "name", "publisherId")(Product.apply)
}

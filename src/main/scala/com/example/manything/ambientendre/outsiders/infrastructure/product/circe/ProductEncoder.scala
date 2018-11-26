package com.example.manything.ambientendre.outsiders.infrastructure.product.circe

import com.example.manything.ambientendre.domain.product.{Product, ProductId}

trait ProductEncoder {
  import io.circe._

  import com.example.manything.ambientendre.outsiders.infrastructure.publisher.circe.PublisherCodec.publisherIdEncoder

  implicit lazy val productEncoder: Encoder[Product] =
    Encoder.forProduct3("id", "name", "publisherId")(p =>
      (p.identity, p.name, p.publisherId))

  implicit lazy val productIdEncoder: Encoder[ProductId] =
    Encoder.encodeString.contramap[ProductId](_.value.toString)
}

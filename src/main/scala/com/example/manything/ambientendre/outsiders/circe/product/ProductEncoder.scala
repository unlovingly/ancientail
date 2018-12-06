package com.example.manything.ambientendre.outsiders.circe.product

import com.example.manything.ambientendre.domain.models.product.{
  Product,
  ProductId
}

trait ProductEncoder {
  import io.circe._

  import com.example.manything.ambientendre.outsiders.circe.publisher.PublisherCodec.publisherIdEncoder

  implicit lazy val productEncoder: Encoder[Product] =
    Encoder.forProduct3("id", "name", "publisherId")(p =>
      (p.identity, p.name, p.publisherId))

  implicit lazy val productIdEncoder: Encoder[ProductId] =
    Encoder.encodeString.contramap[ProductId](_.value.toString)
}

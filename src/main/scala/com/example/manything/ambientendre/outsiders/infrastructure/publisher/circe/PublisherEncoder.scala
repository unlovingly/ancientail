package com.example.manything.ambientendre.outsiders.infrastructure.publisher.circe

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}

trait PublisherEncoder {
  import io.circe.Encoder

  implicit lazy val publisherIdEncoder: Encoder[PublisherId] =
    Encoder.encodeString.contramap[PublisherId](_.value.toString)

  implicit lazy val publisherEncoder: Encoder[Publisher] =
    Encoder.forProduct2("id", "name")(p => (p.identity, p.name))
}

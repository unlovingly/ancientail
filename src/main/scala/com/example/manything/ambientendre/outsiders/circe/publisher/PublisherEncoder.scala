package com.example.manything.ambientendre.outsiders.circe.publisher

import com.example.manything.ambientendre.domain.models.publisher.{
  Publisher,
  PublisherId
}

trait PublisherEncoder {
  import io.circe.Encoder

  implicit lazy val publisherIdEncoder: Encoder[PublisherId] =
    Encoder.encodeString.contramap[PublisherId](_.value.toString)

  implicit lazy val publisherEncoder: Encoder[Publisher] =
    Encoder.forProduct2("identity", "name")(p => (p.identity, p.name))
}

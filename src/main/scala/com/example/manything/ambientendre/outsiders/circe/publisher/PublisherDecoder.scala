package com.example.manything.ambientendre.outsiders.circe.publisher

import java.util.UUID

import com.example.manything.ambientendre.domain.models.publisher.{
  Publisher,
  PublisherId
}

trait PublisherDecoder {
  import cats.syntax.either._

  import io.circe.Decoder

  implicit lazy val publisherIdDecoder: Decoder[PublisherId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(PublisherId(UUID.fromString(str)))
        .leftMap(_ => "PublisherId")
    }

  implicit lazy val publisherDecoder: Decoder[Publisher] =
    Decoder.forProduct2("identity", "name")(Publisher.apply)
}

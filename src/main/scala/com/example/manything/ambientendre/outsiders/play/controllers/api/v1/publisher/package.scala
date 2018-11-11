package com.example.manything.ambientendre.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}

package object publisher {
  import cats.syntax.either._

  import io.circe.{Decoder, Encoder}

  implicit val encodePublisherId: Encoder[PublisherId] =
    Encoder.encodeString.contramap[PublisherId](_.value.toString)

  implicit val decodePublisherId: Decoder[PublisherId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(PublisherId(UUID.fromString(str)))
        .leftMap(_ => "PublisherId")
    }

  implicit val publisherDecoder: Decoder[Publisher] =
    Decoder.forProduct2("identity", "name")(Publisher.apply)

}

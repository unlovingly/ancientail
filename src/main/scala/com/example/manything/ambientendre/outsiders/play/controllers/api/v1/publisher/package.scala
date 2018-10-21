package com.example.manything.ambientendre.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.roundelayout.domain.Identifiability

package object publisher {
  import cats.syntax.either._
  import io.circe.{Decoder, Encoder}

  implicit val encodePublisherId: Encoder[PublisherId] =
    Encoder.encodeString.contramap[PublisherId](_.value.toString)

  implicit val decodePublisherId: Decoder[PublisherId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(Identifiability[UUID, Publisher](UUID.fromString(str)))
        .leftMap(t => "PublisherId")
    }

  implicit val publisherDecoder: Decoder[Publisher] =
    Decoder.forProduct2("identity", "name")(Publisher.apply)

}

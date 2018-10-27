package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ancientail.domain.slip._
import com.example.manything.roundelayout.domain.Identifiability

package object slip {
  import cats.syntax.either._
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.decodePublisherId
  import com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop.decodeShopId
  import io.circe.generic.auto._
  import io.circe.{Decoder, Encoder}

  implicit val encodeSlipId: Encoder[SlipId] =
    Encoder.encodeString.contramap[SlipId](_.value.toString)

  implicit val decodeSlipId: Decoder[SlipId] = Decoder.decodeString.emap {
    str =>
      Either
        .catchNonFatal(Identifiability[UUID, Slip](UUID.fromString(str)))
        .leftMap(_ => "SlipId")
  }

  implicit val slipDecoder: Decoder[Slip] =
    Decoder.forProduct4("identity", "senderId", "receiverId", "items")(
      Slip.apply)
}

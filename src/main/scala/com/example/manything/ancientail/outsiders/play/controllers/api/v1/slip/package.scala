package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import com.example.manything.ancientail.domain.slip._
import com.example.manything.roundelayout.domain.Identifiability

package object slip {
  import cats.syntax.either._
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.decodeProductId
  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.decodePublisherId
  import com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop.decodeShopId
  import io.circe.{Decoder, Encoder}

  implicit lazy val encodeSlipId: Encoder[SlipId] =
    Encoder.encodeString.contramap[SlipId](_.value.toString)

  implicit lazy val slipIdOptionDecoder: Decoder[Option[SlipId]] =
    Decoder.decodeOption

  implicit lazy val slipIdDecoder: Decoder[SlipId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(Identifiability[UUID, Slip[_]](UUID.fromString(str)))
        .leftMap(_ => "SlipId")
    }

  implicit lazy val slipItemIdOptionDecoder: Decoder[Option[SlipItemId]] =
    Decoder.decodeOption

  implicit lazy val slipItemIdDecoder: Decoder[SlipItemId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(Identifiability[UUID, SlipItem](UUID.fromString(str)))
        .leftMap(_ => "SlipItemId")
    }

  implicit lazy val slipItemDecoder: Decoder[SlipItem] =
    Decoder.forProduct4("identity", "productId", "amount", "price")(
      SlipItem.apply)

  implicit lazy val exchangeSlipDecoder: Decoder[ExchangeSlip] =
    Decoder.forProduct4("identity", "senderId", "receiverId", "items")(
      ExchangeSlip.apply)

  implicit lazy val purchaseSlipDecoder: Decoder[PurchaseSlip] =
    Decoder.forProduct4("identity", "senderId", "receiverId", "items")(
      PurchaseSlip.apply)
}

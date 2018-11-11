package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import play.api.mvc.PathBindable

import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.slip.sales.SalesSlip

package object slip {
  import cats.syntax.either._

  import io.circe.generic.auto._
  import io.circe.syntax._
  import io.circe.{Decoder, Encoder}

  import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.decodeProductId

  implicit lazy val slipIdEncoder: Encoder[SlipId] =
    Encoder.encodeString.contramap[SlipId](_.value.toString)

  implicit lazy val slipIdOptionDecoder: Decoder[Option[SlipId]] =
    Decoder.decodeOption

  implicit lazy val slipIdDecoder: Decoder[SlipId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(SlipId(UUID.fromString(str)))
        .leftMap(_ => "SlipId")
    }

  implicit def pathBinder(
    implicit uuidBinder: PathBindable[UUID]): PathBindable[SlipId] =
    new PathBindable[SlipId] {
      override def bind(key: String, value: String): Either[String, SlipId] =
        uuidBinder.bind(key, value).map(SlipId.apply)
      override def unbind(key: String, value: SlipId): String =
        value.value.toString
    }

  implicit val slipBaseEncoder: Encoder[SlipBase] = Encoder.instance {
    case e @ ExchangeSlip(_, _, _, _, _) => e.asJson
    case e @ PurchaseSlip(_, _, _, _, _) => e.asJson
  }

  implicit lazy val slipItemIdOptionDecoder: Decoder[Option[SlipItemId]] =
    Decoder.decodeOption

  implicit lazy val slipItemIdDecoder: Decoder[SlipItemId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(SlipItemId(UUID.fromString(str)))
        .leftMap(_ => "SlipItemId")
    }

  implicit lazy val slipItemDecoder: Decoder[SlipItem] =
    Decoder.forProduct4("identity", "productId", "amount", "price")(
      SlipItem.apply)

  implicit lazy val purchaseSlipDecoder: Decoder[PurchaseSlip] =
    Decoder.forProduct5("identity",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt")(PurchaseSlip.apply)

  implicit lazy val salesSlipDecoder: Decoder[SalesSlip] =
    Decoder.forProduct5("identity",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt")(SalesSlip.apply)

  implicit lazy val exchangeSlipDecoder: Decoder[ExchangeSlip] =
    Decoder.forProduct5("identity",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt")(ExchangeSlip.apply)
}

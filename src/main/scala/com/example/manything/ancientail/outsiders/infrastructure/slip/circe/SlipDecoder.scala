package com.example.manything.ancientail.outsiders.infrastructure.slip.circe

import java.util.UUID

import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.slip.sales.SalesSlip

trait SlipDecoder {
  import cats.syntax.either.catsSyntaxEither
  import cats.syntax.either.catsSyntaxEitherObject

  import io.circe.generic.auto._
  import io.circe.Decoder

  implicit lazy val slipIdDecoder: Decoder[SlipId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(SlipId(UUID.fromString(str)))
        .leftMap(_ => "SlipId")
    }

  implicit lazy val purchaseSlipDecoder: Decoder[PurchaseSlip] =
    Decoder.forProduct7("identity",
                        "number",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt",
                        "approvedAt")(PurchaseSlip.apply)

  implicit lazy val salesSlipDecoder: Decoder[SalesSlip] =
    Decoder.forProduct7("identity",
                        "number",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt",
                        "approvedAt")(SalesSlip.apply)

  implicit lazy val exchangeSlipDecoder: Decoder[ExchangeSlip] =
    Decoder.forProduct7("identity",
                        "number",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt",
                        "approvedAt")(ExchangeSlip.apply)
}

package com.example.manything.ancientail.outsiders.circe.slip

import java.util.UUID

import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip

trait SlipDecoder {
  import cats.syntax.either.{catsSyntaxEither, catsSyntaxEitherObject}

  import io.circe.Decoder
  import io.circe.generic.auto._
  import io.circe.java8.time.decodeZonedDateTime

  import com.example.manything.ambientendre.outsiders.circe.publisher.PublisherCodec.publisherIdDecoder
  import com.example.manything.ancientail.outsiders.circe.shop.PluCodeCodec.pluCodeDecoder
  import com.example.manything.ancientail.outsiders.circe.shop.ShopCodec.shopIdDecoder
  import com.example.manything.blessedict.outsiders.circe.CustomerCodec.customerIdDecoder
  import com.example.manything.ancientail.outsiders.circe.slip.SlipItemCodec.purchaseSlipItemDecoder

  implicit lazy val slipIdDecoder: Decoder[SlipId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(SlipId(UUID.fromString(str)))
        .leftMap(_ => "SlipId")
    }

  implicit lazy val exchangeSlipDecoder: Decoder[ExchangeSlip] =
    Decoder.forProduct7("identity",
                        "number",
                        "senderId",
                        "receiverId",
                        "items",
                        "publishedAt",
                        "approvedAt")(ExchangeSlip.apply)

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
}

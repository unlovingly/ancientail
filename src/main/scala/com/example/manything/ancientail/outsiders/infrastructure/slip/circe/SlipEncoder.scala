package com.example.manything.ancientail.outsiders.infrastructure.slip.circe

import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip

trait SlipEncoder {
  import io.circe.Encoder
  import io.circe.java8.time.decodeZonedDateTime
  import io.circe.generic.auto._
  import io.circe.generic.semiauto.deriveEncoder

  import com.example.manything.ambientendre.outsiders.infrastructure.publisher.circe.PublisherCodec.publisherIdEncoder
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.ShopCodec.shopIdEncoder
  import com.example.manything.ancientail.outsiders.infrastructure.slip.circe.SlipItemCodec.slipItemEncoder

  implicit lazy val slipIdEncoder: Encoder[SlipId] =
    Encoder.encodeString.contramap[SlipId](_.value.toString)

  implicit lazy val exchangeSlipEncoder: Encoder[ExchangeSlip] =
    deriveEncoder

  implicit lazy val purchaseSlipEncoder: Encoder[PurchaseSlip] =
    deriveEncoder

  implicit lazy val salesSlipEncoder: Encoder[SalesSlip] =
    deriveEncoder
}

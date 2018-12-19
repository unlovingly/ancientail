package com.example.manything.ancientail.outsiders.circe.slip

import com.example.manything.ancientail.domain.models.slip.SlipItemId
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlipItem
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlipItem
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlipItem

trait SlipItemEncoder {
  import io.circe.Encoder
  import io.circe.generic.semiauto.deriveEncoder

  import com.example.manything.ambientendre.outsiders.circe.product.ProductCodec.productIdEncoder
  import com.example.manything.ancientail.outsiders.circe.shop.PluCodeCodec.pluCodeEncoder

  implicit lazy val slipItemIdEncoder: Encoder[SlipItemId] =
    Encoder.encodeString.contramap[SlipItemId](_.value.toString)

  implicit lazy val exchangeSlipItemEncoder: Encoder[ExchangeSlipItem] =
    deriveEncoder

  implicit lazy val purchaseSlipItemEncoder: Encoder[PurchaseSlipItem] =
    deriveEncoder

  implicit lazy val salesSlipItemEncoder: Encoder[SalesSlipItem] =
    deriveEncoder
}

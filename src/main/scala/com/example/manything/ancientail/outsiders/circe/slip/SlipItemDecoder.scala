package com.example.manything.ancientail.outsiders.circe.slip

import java.util.UUID

import com.example.manything.ancientail.domain.models.slip.SlipItemId
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlipItem
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlipItem
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlipItem

trait SlipItemDecoder {
  import cats.syntax.either.{catsSyntaxEither, catsSyntaxEitherObject}

  import io.circe.Decoder

  import com.example.manything.ambientendre.outsiders.circe.product.ProductCodec.productIdDecoder
  import com.example.manything.ancientail.outsiders.circe.shop.PluCodeCodec.pluCodeDecoder

  implicit lazy val slipItemIdDecoder: Decoder[SlipItemId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(SlipItemId(UUID.fromString(str)))
        .leftMap(_ => "SlipItemId")
    }

  // TODO productId のバリデーション (空になることがある)
  implicit lazy val exchangeSlipItemDecoder: Decoder[ExchangeSlipItem] =
    Decoder.forProduct4("identity", "productId", "amount", "price")(
      ExchangeSlipItem.apply)
  implicit lazy val purchaseSlipItemDecoder: Decoder[PurchaseSlipItem] =
    Decoder.forProduct4("identity", "productId", "amount", "price")(
      PurchaseSlipItem.apply)
  implicit lazy val salesSlipItemDecoder: Decoder[SalesSlipItem] =
    Decoder.forProduct4("identity", "productId", "amount", "price")(
      SalesSlipItem.apply)
}

package com.example.manything.ancientail.outsiders.infrastructure.slip.circe

import java.util.UUID

import com.example.manything.ancientail.domain.slip.{SlipItem, SlipItemId}

trait SlipItemDecoder {
  import cats.syntax.either._

  import io.circe.Decoder

  import com.example.manything.ambientendre.outsiders.infrastructure.product.circe.ProductCodec.productIdDecoder

  implicit lazy val slipItemIdDecoder: Decoder[SlipItemId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(SlipItemId(UUID.fromString(str)))
        .leftMap(_ => "SlipItemId")
    }

  implicit lazy val slipItemDecoder: Decoder[SlipItem] =
    Decoder.forProduct4("id", "productId", "amount", "price")(SlipItem.apply)
}

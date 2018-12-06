package com.example.manything.ancientail.outsiders.infrastructure.slip.circe

import com.example.manything.ancientail.domain.models.slip.{
  SlipItem,
  SlipItemId
}

trait SlipItemEncoder {
  import io.circe.Encoder
  import io.circe.generic.semiauto.deriveEncoder

  import com.example.manything.ambientendre.outsiders.infrastructure.product.circe.ProductCodec.productIdEncoder

  implicit lazy val slipItemIdEncoder: Encoder[SlipItemId] =
    Encoder.encodeString.contramap[SlipItemId](_.value.toString)

  implicit lazy val slipItemEncoder: Encoder[SlipItem] =
    deriveEncoder
}

package com.example.manything.ancientail.outsiders.circe.slip

import com.example.manything.ancientail.domain.models.slip.{
  SlipItem,
  SlipItemId
}

trait SlipItemEncoder {
  import io.circe.Encoder

  import com.example.manything.ambientendre.outsiders.circe.product.ProductCodec.productIdEncoder

  implicit lazy val slipItemIdEncoder: Encoder[SlipItemId] =
    Encoder.encodeString.contramap[SlipItemId](_.value.toString)
}

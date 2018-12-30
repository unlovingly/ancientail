package com.example.manything.ancientail.outsiders.circe.shop

import com.example.manything.ancientail.domain.models.shop.PluCode

trait PluCodeEncoder {
  import io.circe.Encoder

  implicit lazy val pluCodeEncoder: Encoder[PluCode] =
    Encoder.encodeString.contramap[PluCode](_.toString)
}

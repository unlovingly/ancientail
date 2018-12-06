package com.example.manything.ancientail.outsiders.infrastructure.shop.circe

import com.example.manything.ancientail.domain.models.shop.PluCode

trait PluCodeEncoder {
  import io.circe.Encoder

  implicit lazy val pluCodeEncoder: Encoder[PluCode] =
    Encoder.encodeString.contramap[PluCode](_.value.toString)
}

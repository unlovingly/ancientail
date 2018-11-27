package com.example.manything.ancientail.outsiders.infrastructure.shop.circe

import com.example.manything.ancientail.domain.shop.PluCode

trait PluCodeDecoder {
  import cats.syntax.either.catsSyntaxEither
  import cats.syntax.either.catsSyntaxEitherObject

  import io.circe.Decoder

  implicit lazy val pluCodeDecoder: Decoder[PluCode] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(PluCode(str))
        .leftMap(_ => "PluCode")
    }

}

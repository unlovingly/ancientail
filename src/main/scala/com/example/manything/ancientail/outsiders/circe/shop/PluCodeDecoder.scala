package com.example.manything.ancientail.outsiders.circe.shop

import com.example.manything.ancientail.domain.models.shop.PluCode

trait PluCodeDecoder {
  import cats.syntax.either.catsSyntaxEither
  import cats.syntax.either.catsSyntaxEitherObject

  import io.circe.Decoder

  implicit lazy val pluCodeDecoder: Decoder[PluCode] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(PluCode.parse(str))
        .leftMap(_ => "PluCode")
    }

}

package com.example.manything.ancientail.outsiders.circe.shop

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import com.example.manything.ancientail.domain.models.shop.PluCode

class PluCodeCodecTest extends FlatSpec with DiagrammedAssertions {
  val code = PluCode("12345")
  val codeAsString = "\"12345\""

  it should "pluCodeDecoder" in {
    import io.circe.parser._

    import PluCodeCodec.pluCodeDecoder

    assert(decode[PluCode](codeAsString) === Right(code))
  }

  it should "pluCodeEncoder" in {
    import io.circe.syntax.EncoderOps

    import PluCodeCodec.pluCodeEncoder

    assert(code.asJson.noSpaces === codeAsString)
  }
}

package com.example.manything.ancientail.outsiders.circe.shop

import java.util.UUID

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.shop.PluCode

class PluCodeCodecTest
  extends FlatSpec
  with DiagrammedAssertions
  with cats.tests.StrictCatsEquality {
  val code = PluCode.generate(ProductId(new UUID(0, 0)), 1000)
  //val codeAsString = "\"00000000-0000-0000-0000-000000000000----1000\""
  val codeAsString = "\"00000000-0000-0000-0000-000000000000----1000\""

  it should "pluCodeDecoder" in {
    import io.circe.parser._
    import cats.implicits._

    import PluCodeCodec.pluCodeDecoder
    import com.example.manything.ancientail.domain.models.shop.PluCode.pluCodeEq

    assert(decode[PluCode](codeAsString) === Right(code))
  }

  it should "pluCodeEncoder" in {
    //import io.circe.syntax.EncoderOps

    //import PluCodeCodec.pluCodeEncoder

    //assert(code.asJson.noSpaces === codeAsString)
  }
}

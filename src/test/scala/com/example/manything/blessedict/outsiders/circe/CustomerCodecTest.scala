package com.example.manything.blessedict.outsiders.circe

import java.util.UUID

import org.scalatest.{ DiagrammedAssertions, FlatSpec }

import com.example.manything.blessedict.domain.models.customer.{ Customer, CustomerId }

class CustomerCodecTest extends FlatSpec with DiagrammedAssertions {
  val id = CustomerId(value = new UUID(0, 0))
  val p = Customer(identity = Some(id), name = "The Customer")

  val ids = s""""00000000-0000-0000-0000-000000000000""""
  val ps = s"""{"identity":$ids,"name":"The Customer"}"""

  it should "customerIdDecoder" in {
    import io.circe.parser._

    import CustomerCodec.customerIdDecoder

    assert(decode[CustomerId](ids) === Right(id))
  }

  it should "customerDecoder" in {
    import io.circe.parser._

    import CustomerCodec.customerDecoder

    assert(decode[Customer](ps) === Right(p))
  }

  it should "customerEncoder" in {
    import io.circe.syntax.EncoderOps

    import CustomerCodec.customerEncoder

    assert(p.asJson.noSpaces === ps)
  }

  it should "customerIdEncoder" in {
    import io.circe.syntax.EncoderOps

    import CustomerCodec.customerIdEncoder

    assert(id.asJson.noSpaces === ids)
  }
}

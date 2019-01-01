package com.example.manything.blessedict.outsiders.circe

import com.example.manything.blessedict.domain.models.customer.{ Customer, CustomerId }

trait CustomerEncoder {
  import io.circe.Encoder
  import io.circe.generic.auto._

  implicit lazy val customerIdEncoder: Encoder[CustomerId] =
    Encoder.encodeString.contramap[CustomerId](_.value.toString)

  implicit lazy val customerEncoder: Encoder[Customer] =
    Encoder.forProduct2("identity", "name")(s => (s.identity, s.name))
}

package com.example.manything.blessedict.outsiders.circe

import com.example.manything.blessedict.domain.customer.{Customer, CustomerId}

trait CustomerEncoder {
  import io.circe.Encoder
  import io.circe.generic.auto._

  implicit lazy val shopIdEncoder: Encoder[CustomerId] =
    Encoder.encodeString.contramap[CustomerId](_.value.toString)

  implicit lazy val shopEncoder: Encoder[Customer] =
    Encoder.forProduct2("id", "name")(s => (s.identity, s.name))
}

package com.example.manything.blessedict.outsiders.circe

import java.util.UUID

import com.example.manything.blessedict.domain.customer.{Customer, CustomerId}

trait CustomerDecoder {
  import cats.syntax.either._

  import io.circe.Decoder

  implicit lazy val customerIdDecoder: Decoder[CustomerId] =
    Decoder.decodeString.emap { str =>
      Either
        .catchNonFatal(CustomerId(UUID.fromString(str)))
        .leftMap(_ => "CustomerId")
    }

  implicit lazy val customerDecoder: Decoder[Customer] =
    Decoder.forProduct2("identity", "name")(Customer.apply)
}

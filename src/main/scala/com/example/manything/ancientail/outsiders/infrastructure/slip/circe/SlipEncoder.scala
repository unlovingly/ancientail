package com.example.manything.ancientail.outsiders.infrastructure.slip.circe

import com.example.manything.ancientail.domain.slip.{SlipBase, SlipId}

trait SlipEncoder {
  import io.circe._
  import io.circe.java8.time.decodeZonedDateTime
  import io.circe.generic.auto._
  import io.circe.generic.semiauto._

  import com.example.manything.ambientendre.outsiders.infrastructure.publisher.circe.PublisherCodec.publisherIdEncoder
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.ShopCodec.shopIdEncoder

  implicit lazy val slipIdEncoder: Encoder[SlipId] =
    Encoder.encodeString.contramap[SlipId](_.value.toString)
}

package com.example.manything.ambientendre.outsiders.circe.publisher

import java.util.UUID

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import com.example.manything.ambientendre.domain.models.publisher.{
  Publisher,
  PublisherId
}

class PublisherCodecTest extends FlatSpec with DiagrammedAssertions {
  val id = PublisherId(value = new UUID(0, 0))
  val p = Publisher(identity = Some(id), name = "The Publisher")

  val ids = s""""00000000-0000-0000-0000-000000000000""""
  val ps = s"""{"identity":$ids,"name":"The Publisher"}"""

  it should "publisherIdDecoder" in {
    import io.circe.parser._

    import PublisherCodec.publisherIdDecoder

    assert(decode[PublisherId](ids) === Right(id))
  }

  it should "publisherEncoder" in {
    import io.circe.syntax.EncoderOps

    import PublisherCodec.publisherEncoder

    assert(p.asJson.noSpaces === ps)
  }

  it should "publisherIdEncoder" in {
    import io.circe.syntax.EncoderOps

    import PublisherCodec.publisherIdEncoder

    assert(id.asJson.noSpaces === ids)
  }

  it should "publisherDecoder" in {
    import io.circe.parser._

    import PublisherCodec.publisherDecoder

    assert(decode[Publisher](ps) === Right(p))
  }
}

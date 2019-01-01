package com.example.manything.ancientail.outsiders.circe.shop

import java.util.UUID

import cats.effect.{ IO, Resource }

import org.scalatest.{ DiagrammedAssertions, FlatSpec }

import com.example.manything.ancientail.domain.models.shop.{ Shop, ShopId }

class ShopCodecTest extends FlatSpec with DiagrammedAssertions {
  val shopId = ShopId(new UUID(0, 0))
  val shopIdAsString = "\"00000000-0000-0000-0000-000000000000\""

  val shop =
    Shop(identity = Some(shopId), name = "The Shop", stocks = Seq.empty)

  val shopAsString: String = Resource
    .fromAutoCloseable(IO { scala.io.Source.fromResource("shop.json") })
    .use(s => IO(s.mkString.trim()))
    .unsafeRunSync()

  it should "shopIdDecoder" in {
    import io.circe.parser._

    import ShopCodec.shopIdDecoder

    assert(decode[ShopId](shopIdAsString) === Right(shopId))
  }

  it should "shopDecoder" in {
    import io.circe.parser._

    import ShopCodec.shopDecoder

    assert(decode[Shop](shopAsString) === Right(shop))
  }

  it should "shopEncoder" in {
    import io.circe.syntax.EncoderOps

    import ShopCodec.shopEncoder

    assert(shop.asJson.spaces2 === shopAsString)
  }

  it should "shopIdEncoder" in {
    import io.circe.syntax.EncoderOps

    import ShopCodec.shopIdEncoder

    assert(shopId.asJson.spaces2 === shopIdAsString)
  }
}

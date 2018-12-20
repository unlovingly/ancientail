package com.example.manything.ancientail.outsiders.circe.slip

import java.util.UUID

import cats.effect.{IO, Resource}

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip.SlipItemId
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlipItem

class SlipItemCodecTest extends FlatSpec with DiagrammedAssertions {
  val slipItemId = SlipItemId(new UUID(0, 0))
  val productId = ProductId(new UUID(0, 0))

  val slipItem = PurchaseSlipItem(identity = Some(slipItemId),
                                  productId = productId,
                                  amount = 1,
                                  price = 100)

  val slipItemIdAsString = "\"00000000-0000-0000-0000-000000000000\""
  val slipItemAsString: String = Resource
    .fromAutoCloseable(IO { scala.io.Source.fromResource("slipItem.json") })
    .use(s => IO(s.mkString.trim()))
    .unsafeRunSync()

  it should "slipItemEncoder" in {
    import io.circe.syntax.EncoderOps

    import SlipItemCodec.purchaseSlipItemEncoder

    assert(slipItem.asJson.spaces2 === slipItemAsString)
  }

  it should "slipItemIdDecoder" in {
    import io.circe.parser._

    import SlipItemCodec.slipItemIdDecoder

    assert(decode[SlipItemId](slipItemIdAsString) === Right(slipItemId))
  }

  it should "slipItemDecoder" in {
    import io.circe.parser._

    import SlipItemCodec.purchaseSlipItemDecoder

    assert(decode[PurchaseSlipItem](slipItemAsString) === Right(slipItem))
  }

  it should "slipItemIdEncoder" in {
    import io.circe.syntax.EncoderOps

    import SlipItemCodec.slipItemIdEncoder

    assert(slipItemId.asJson.spaces2 === slipItemIdAsString)
  }
}

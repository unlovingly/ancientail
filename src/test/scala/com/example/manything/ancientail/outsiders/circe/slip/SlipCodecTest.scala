package com.example.manything.ancientail.outsiders.circe.slip

import java.time.ZonedDateTime
import java.util.UUID

import cats.effect.{ IO, Resource }

import org.scalatest.{ DiagrammedAssertions, FlatSpec }

import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip
import com.example.manything.blessedict.domain.models.customer.CustomerId

class SlipCodecTest extends FlatSpec with DiagrammedAssertions {
  val slipId = SlipId(new UUID(0, 0))

  val customerId = CustomerId(new UUID(0, 0))
  val publisherId = PublisherId(new UUID(0, 0))
  val shopId = ShopId(new UUID(0, 0))

  val dateTime = ZonedDateTime.parse("2011-12-03T10:15:30+01:00[Europe/Paris]")

  val slipIdAsString = "\"00000000-0000-0000-0000-000000000000\""

  val exchangeSlip = ExchangeSlip(
    identity = Some(slipId),
    number = "1",
    senderId = shopId,
    receiverId = shopId,
    publishedAt = dateTime,
    approvedAt = dateTime,
    items = Seq.empty
  )
  val exchangeSlipAsString = Resource
    .fromAutoCloseable(IO { scala.io.Source.fromResource("slip.json") })
    .use(s => IO(s.mkString.trim()))
    .unsafeRunSync()

  val purchaseSlip = PurchaseSlip(
    identity = Some(slipId),
    number = "1",
    senderId = publisherId,
    receiverId = shopId,
    publishedAt = dateTime,
    approvedAt = dateTime,
    items = Seq.empty
  )
  val purchaseSlipAsString = Resource
    .fromAutoCloseable(IO { scala.io.Source.fromResource("slip.json") })
    .use(s => IO(s.mkString.trim()))
    .unsafeRunSync()

  val salesSlip = SalesSlip(
    identity = Some(slipId),
    number = "1",
    senderId = shopId,
    receiverId = Some(customerId),
    publishedAt = dateTime,
    approvedAt = dateTime,
    items = Seq.empty
  )
  val salesSlipAsString = Resource
    .fromAutoCloseable(IO { scala.io.Source.fromResource("slip.json") })
    .use(s => IO(s.mkString.trim()))
    .unsafeRunSync()

  it should "salesSlipDecoder" in {
    import io.circe.parser._

    import SlipCodec.salesSlipDecoder

    assert(decode[SalesSlip](salesSlipAsString) === Right(salesSlip))
  }

  it should "purchaseSlipDecoder" in {
    import io.circe.parser._

    import SlipCodec.purchaseSlipDecoder

    assert(decode[PurchaseSlip](purchaseSlipAsString) === Right(purchaseSlip))
  }

  it should "salesSlipEncoder" in {
    import io.circe.syntax.EncoderOps

    import SlipCodec.salesSlipEncoder

    assert(salesSlip.asJson.spaces2 === salesSlipAsString)
  }

  it should "exchangeSlipDecoder" in {
    import io.circe.parser._

    import SlipCodec.exchangeSlipDecoder

    assert(decode[ExchangeSlip](exchangeSlipAsString) === Right(exchangeSlip))
  }

  it should "slipIdDecoder" in {
    import io.circe.parser._

    import SlipCodec.slipIdDecoder

    assert(decode[SlipId](slipIdAsString) === Right(slipId))
  }

  it should "exchangeSlipEncoder" in {
    import io.circe.syntax.EncoderOps

    import SlipCodec.exchangeSlipEncoder

    assert(exchangeSlip.asJson.spaces2 === exchangeSlipAsString)
  }

  it should "slipIdEncoder" in {
    import io.circe.syntax.EncoderOps

    import SlipCodec.slipIdEncoder

    assert(slipId.asJson.noSpaces === slipIdAsString)
  }

  it should "purchaseSlipEncoder" in {
    import io.circe.syntax.EncoderOps

    import SlipCodec.purchaseSlipEncoder

    assert(purchaseSlip.asJson.spaces2 === purchaseSlipAsString)
  }
}

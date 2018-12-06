package com.example.manything.ancientail.domain.shop

import java.time._
import java.util.UUID

import org.scalatest._

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.ancientail.domain.models.shop._
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip

class ShopSpec extends FlatSpec with DiagrammedAssertions {
  val receiverId: ShopId = ShopId(value = new UUID(0, 0))
  val senderId: ShopId = ShopId(value = new UUID(0, 1))

  val publisherId: PublisherId = PublisherId(value = new UUID(1, 1))

  val productId1: ProductId = ProductId(new UUID(2, 1))
  val productId2: ProductId = ProductId(new UUID(2, 2))

  val slipItem1Id: SlipItemId = SlipItemId(new UUID(4, 1))
  val slipItem2Id: SlipItemId = SlipItemId(new UUID(4, 2))
  val slipItem3Id: SlipItemId = SlipItemId(new UUID(4, 3))
  val slipItem4Id: SlipItemId = SlipItemId(new UUID(4, 4))

  val purchaseSlip: PurchaseSlip = PurchaseSlip(
    identity = Some(SlipId(new UUID(3, 1))),
    number = "00001",
    senderId = publisherId,
    receiverId = receiverId,
    items = Seq(
      SlipItem(identity = Some(slipItem1Id),
               productId = productId1,
               amount = 1,
               price = 1000),
      SlipItem(identity = Some(slipItem2Id),
               productId = productId1,
               amount = 1,
               price = 2000),
      SlipItem(identity = Some(slipItem3Id),
               productId = productId2,
               amount = 2,
               price = 1000),
      SlipItem(identity = Some(slipItem4Id),
               productId = productId2,
               amount = 1,
               price = 1000)
    ),
    publishedAt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
  )

  val exchangeSlip: ExchangeSlip = ExchangeSlip(
    identity = Some(SlipId(new UUID(3, 1))),
    number = "00001",
    senderId = senderId,
    receiverId = receiverId,
    items = Seq(
      SlipItem(identity = Some(slipItem1Id),
               productId = productId1,
               amount = 1,
               price = 1000),
      SlipItem(identity = Some(slipItem2Id),
               productId = productId1,
               amount = 2,
               price = 2000),
      SlipItem(identity = Some(slipItem4Id),
               productId = productId2,
               amount = 6,
               price = 1000)
    ),
    publishedAt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
  )

  val suspect1: Shop = Shop(
    identity = Some(receiverId),
    name = "Shop One",
    stocks = Seq(
      Stock(pluCode = PluCode.generate(productId1, 2000),
            shopId = receiverId,
            productId = productId1,
            amount = 1,
            price = 2000),
      Stock(pluCode = PluCode.generate(productId2, 1000),
            shopId = receiverId,
            productId = productId2,
            amount = 3,
            price = 1000)
    )
  )

  val receiver: Shop = Shop(
    identity = Some(receiverId),
    name = "Shop One",
    stocks = Seq.empty
  )

  val sender: Shop = Shop(
    identity = Some(senderId),
    name = "Shop Two",
    stocks = Seq(
      Stock(pluCode = PluCode.generate(productId1, 1000),
            shopId = receiverId,
            productId = productId1,
            amount = 2,
            price = 1000),
      Stock(pluCode = PluCode.generate(productId1, 2000),
            shopId = receiverId,
            productId = productId1,
            amount = 4,
            price = 2000),
      Stock(pluCode = PluCode.generate(productId2, 1000),
            shopId = receiverId,
            productId = productId2,
            amount = 12,
            price = 1000)
    )
  )

  val expected: Shop = Shop(
    identity = Some(receiverId),
    name = "Shop One",
    stocks = Seq(
      Stock(pluCode = PluCode.generate(productId1, 1000),
            shopId = receiverId,
            productId = productId1,
            amount = 1,
            price = 1000),
      Stock(pluCode = PluCode.generate(productId1, 2000),
            shopId = receiverId,
            productId = productId1,
            amount = 2,
            price = 2000),
      Stock(pluCode = PluCode.generate(productId2, 1000),
            shopId = receiverId,
            productId = productId2,
            amount = 6,
            price = 1000)
    )
  )

  "method storing" should "update Shop.stocks" in {
    val sorter = (s: Seq[Stock]) => s.sortBy(t => t.pluCode.value)

    val crime = sorter(suspect1.storing(purchaseSlip).stocks)
    val expect = sorter(expected.stocks)

    assert(crime === expect)
  }

  "inbound" should "increase stocks" in {
    val sorter = (s: Seq[Stock]) => s.sortBy(t => t.pluCode.value)

    val crime = sorter(receiver.inbound(exchangeSlip).stocks)
    val expect = sorter(expected.stocks)

    assert(crime === expect)
  }

  "outbonud" should "decrease stocks" in {
    val sorter =
      (s: Seq[Stock]) => s.sortBy(t => (t.productId.value, t.price, t.amount))

    val crime = sorter(sender.outbound(exchangeSlip).stocks)
    val expect = sorter(expected.stocks)

    assert(crime === expect)
  }
}

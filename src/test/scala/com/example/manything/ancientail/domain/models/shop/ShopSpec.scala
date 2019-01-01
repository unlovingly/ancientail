package com.example.manything.ancientail.domain.models.shop

import java.time._
import java.util.UUID

import org.scalatest._

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.ancientail.domain.models.slip._
import com.example.manything.ancientail.domain.models.slip.sales.{ SalesSlip, SalesSlipItem }
import com.example.manything.ancientail.domain.models.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipItem
}
import com.example.manything.blessedict.domain.models.customer.CustomerId

class ShopSpec extends FlatSpec with DiagrammedAssertions with cats.tests.StrictCatsEquality {
  val receiverId: ShopId = ShopId(value = new UUID(0, 0))
  val senderId: ShopId = ShopId(value = new UUID(0, 1))

  val customerId: CustomerId = CustomerId(value = new UUID(5, 0))

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
      PurchaseSlipItem(
        identity = Some(slipItem1Id),
        productId = productId1,
        amount = 1,
        price = 1000
      ),
      PurchaseSlipItem(
        identity = Some(slipItem2Id),
        productId = productId1,
        amount = 1,
        price = 2000
      ),
      PurchaseSlipItem(
        identity = Some(slipItem4Id),
        productId = productId2,
        amount = 6,
        price = 1000
      )
    ),
    publishedAt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
  )

  val salesSlip: SalesSlip = SalesSlip(
    identity = Some(SlipId(new UUID(3, 1))),
    number = "00001",
    senderId = senderId,
    receiverId = None,
    items = Seq(
      SalesSlipItem(
        identity = Some(slipItem1Id),
        pluCode = PluCode.generate(productId1, 1000),
        amount = 1,
        price = 1000
      ),
      SalesSlipItem(
        identity = Some(slipItem2Id),
        pluCode = PluCode.generate(productId1, 2000),
        amount = 2,
        price = 2000
      ),
      SalesSlipItem(
        identity = Some(slipItem4Id),
        pluCode = PluCode.generate(productId2, 1000),
        amount = 6,
        price = 1000
      )
    ),
    publishedAt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
  )

  val suspect1: Shop = Shop(
    identity = Some(receiverId),
    name = "Shop One",
    stocks = Seq(
      Stock(
        pluCode = PluCode.generate(productId1, 2000),
        shopId = receiverId,
        productId = productId1,
        amount = 1,
        price = 2000
      ),
      Stock(
        pluCode = PluCode.generate(productId2, 1000),
        shopId = receiverId,
        productId = productId2,
        amount = 3,
        price = 1000
      )
    )
  )

  val receiver: Shop = Shop(
    identity = Some(receiverId),
    name = "Shop One",
    stocks = Seq(
      Stock(
        pluCode = PluCode.generate(productId1, 2000),
        shopId = receiverId,
        productId = productId1,
        amount = 1,
        price = 2000
      )
    )
  )

  val sender: Shop = Shop(
    identity = Some(senderId),
    name = "Shop Two",
    stocks = Seq(
      Stock(
        pluCode = PluCode.generate(productId1, 1000),
        shopId = receiverId,
        productId = productId1,
        amount = 2,
        price = 1000
      ),
      Stock(
        pluCode = PluCode.generate(productId1, 2000),
        shopId = receiverId,
        productId = productId1,
        amount = 4,
        price = 2000
      ),
      Stock(
        pluCode = PluCode.generate(productId2, 1000),
        shopId = receiverId,
        productId = productId2,
        amount = 12,
        price = 1000
      )
    )
  )

  val expected: Shop = Shop(
    identity = Some(receiverId),
    name = "Shop One",
    stocks = Seq(
      Stock(
        pluCode = PluCode.generate(productId1, 1000),
        shopId = receiverId,
        productId = productId1,
        amount = 1,
        price = 1000
      ),
      Stock(
        pluCode = PluCode.generate(productId1, 2000),
        shopId = receiverId,
        productId = productId1,
        amount = 2,
        price = 2000
      ),
      Stock(
        pluCode = PluCode.generate(productId2, 1000),
        shopId = receiverId,
        productId = productId2,
        amount = 6,
        price = 1000
      )
    )
  )

  "storing" should "increase stocks" in {
    import Stock.stockEq
    import cats.implicits.catsKernelStdEqForList

    val sorter = (s: Seq[Stock]) => s.sortBy(t => t.pluCode.toString)

    val crime = sorter(receiver.storing(purchaseSlip).stocks).toList
    val expect = sorter(expected.stocks).toList

    assert(crime === expect)
  }

  "sell" should "decrease stocks" in {
    import Stock.stockEq
    import cats.implicits.catsKernelStdEqForList

    val sorter =
      (s: Seq[Stock]) => s.sortBy(t => (t.productId.value, t.price, t.amount))

    val crime = sorter(sender.sell(salesSlip).stocks).toList
    val expect = sorter(expected.stocks).toList

    assert(crime === expect)
  }
}

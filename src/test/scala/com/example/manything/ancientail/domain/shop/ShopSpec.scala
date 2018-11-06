package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.slip._
import org.scalatest._

class ShopSpec extends FlatSpec with DiagrammedAssertions {
  val shopId: ShopId = ShopId(
    value = UUID.fromString("00000000-0000-0000-0000-000000000000"))
  val publisherId: PublisherId = PublisherId(
    value = UUID.fromString("00000000-0000-0001-0000-000000000001"))

  val productId1: ProductId = ProductId(
    UUID.fromString("00000000-0000-0002-0000-000000000001"))
  val productId2: ProductId = ProductId(
    UUID.fromString("00000000-0000-0002-0000-000000000002"))

  val slipId: SlipId = SlipId(
    UUID.fromString("00000000-0000-0003-0000-000000000001"))

  val slipItem1Id: SlipItemId = SlipItemId(
    UUID.fromString("00000000-0000-0004-0000-000000000001"))
  val slipItem2Id: SlipItemId = SlipItemId(
    UUID.fromString("00000000-0000-0004-0000-000000000002"))
  val slipItem3Id: SlipItemId = SlipItemId(
    UUID.fromString("00000000-0000-0004-0000-000000000003"))
  val slipItem4Id: SlipItemId = SlipItemId(
    UUID.fromString("00000000-0000-0004-0000-000000000004"))

  val slip: Slip = Slip(
    identity = Some(slipId),
    senderId = publisherId,
    receiverId = shopId,
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
    )
  )

  val suspect: Shop = Shop(
    identity = Some(shopId),
    name = "Shop One",
    stocks = Seq.empty
  )

  val expected: Shop = Shop(
    identity = Some(shopId),
    name = "Shop One",
    stocks = Seq(
      Stock(pluCode = PluCode.generate(shopId, productId1, 1000),
            shopId = shopId,
            productId = productId1,
            amount = 1,
            price = 1000),
      Stock(pluCode = PluCode.generate(shopId, productId1, 2000),
            shopId = shopId,
            productId = productId1,
            amount = 1,
            price = 2000),
      Stock(pluCode = PluCode.generate(shopId, productId2, 1000),
            shopId = shopId,
            productId = productId2,
            amount = 3,
            price = 1000)
    )
  )

  "method storing" should "update Shop.stocks" in {
    val sorter = (s: Seq[Stock]) => s.sortBy(t => t.pluCode.value)

    val crime = sorter(suspect.storing(slip).stocks)
    val expect = sorter(expected.stocks)

    assert(crime === expect)
  }
}

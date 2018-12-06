package com.example.manything.ancientail.domain.shop

import java.util.UUID

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.shop._
import com.example.manything.ancientail.domain.slip.Price

class StockSpec extends FlatSpec with DiagrammedAssertions {
  val shopId: ShopId = ShopId(value = new UUID(0, 0))

  val productId1: ProductId = ProductId(new UUID(2, 1))
  val productId2: ProductId = ProductId(new UUID(2, 2))

  val generator = (pId: ProductId, price: Price) =>
    Stock(pluCode = PluCode.generate(pId, price),
          shopId = shopId,
          productId = pId,
          amount = 1,
          price = price)

  val stock1 = generator(productId1, 1000)
  val stock2 = generator(productId1, 1000)
  val stock3 = generator(productId1, 2000)
  val stock4 = generator(productId2, 1000)

  "method +" should "add amount" in {
    val stock = stock1 + stock2

    assert(stock.amount === 2)
  }

  "method +" should "productId が違うときはなにもしない" in {
    val stock = stock1 + stock4

    assert(stock.amount === 1)
  }

  "method +" should "price が違うときはなにもしない" in {
    val stock = stock1 + stock3

    assert(stock.amount === 1)
  }

  "method -" should "sub amount" in {
    val stock = stock1 - stock2

    assert(stock.amount === 0)
  }

  "method -" should "productId が違うときはなにもしない" in {
    val stock = stock1 - stock4

    assert(stock.amount === 1)
  }

  "method -" should "price が違うときはなにもしない" in {
    val stock = stock1 - stock3

    assert(stock.amount === 1)
  }
}

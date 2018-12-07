package com.example.manything.ancientail.outsiders.circe.shop

import java.util.UUID

import cats.effect.{IO, Resource}

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.shop._

class StockCodecTest extends FlatSpec with DiagrammedAssertions {
  val pluCode = PluCode("1")
  val shopId = ShopId(new UUID(0, 0))
  val productId = ProductId(new UUID(0, 0))
  val stock = Stock(pluCode = pluCode,
                    shopId = shopId,
                    productId = productId,
                    amount = 1,
                    price = 100)

  val stockAsString = Resource
    .fromAutoCloseable(IO { scala.io.Source.fromResource("stock.json") })
    .use(s => IO(s.mkString.trim()))
    .unsafeRunSync()

  it should "stockEncoder" in {
    import io.circe.syntax.EncoderOps

    import StockCodec.stockEncoder

    assert(stock.asJson.spaces2 === stockAsString)
  }

  it should "stockDecoder" in {
    import io.circe.parser._

    import StockCodec.stockDecoder

    assert(decode[Stock](stockAsString) === Right(stock))
  }
}

package com.example.manything.ambientendre.outsiders.circe.product

import java.util.UUID

import org.scalatest.{ DiagrammedAssertions, FlatSpec }

import com.example.manything.ambientendre.domain.models.product.{ Product, ProductId }
import com.example.manything.ambientendre.domain.models.publisher.PublisherId

class ProductCodecTest extends FlatSpec with DiagrammedAssertions {
  val name = "The Product"
  val publisherId = PublisherId(value = new UUID(0, 0))
  val productId = ProductId(value = new UUID(0, 0))

  val product = Product(
    identity = Some(productId),
    name = "The Product",
    publisherId = publisherId
  )

  val productIdAsString = s""""00000000-0000-0000-0000-000000000000""""
  val publisherIdAsString = s""""00000000-0000-0000-0000-000000000000""""
  val productAsString =
    s"""{"identity":$productIdAsString,"name":"$name","publisherId":$publisherIdAsString}"""

  it should "productDecoder" in {
    import io.circe.parser._

    import ProductCodec.productDecoder

    assert(decode[Product](productAsString) === Right(product))
  }

  it should "productIdDecoder" in {
    import io.circe.parser._

    import ProductCodec.productIdDecoder

    assert(decode[ProductId](productIdAsString) === Right(productId))
  }

  it should "productIdEncoder" in {
    import io.circe.syntax.EncoderOps

    import ProductCodec.productIdEncoder

    assert(productId.asJson.noSpaces === productIdAsString)
  }

  it should "productEncoder" in {
    import io.circe.syntax.EncoderOps

    import ProductCodec.productEncoder

    assert(product.asJson.noSpaces === productAsString)
  }
}

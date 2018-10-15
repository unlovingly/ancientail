package com.example.manything.ambientendre.outsiders.play

import com.example.manything.ambientendre.domain.product
import com.example.manything.ambientendre.domain.product.{Product, ProductId}
import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import play.api.data.Forms._
import play.api.data.{Form, Mapping}

package object forms {
  implicit lazy val publisherIdFormatter: PublisherIdFormatter =
    new PublisherIdFormatter {}

  implicit lazy val publisherMapping: Mapping[Publisher] = mapping(
    "identity" -> optional(of[PublisherId](publisherIdFormatter)),
    "name" -> text
  )(Publisher.apply)(Publisher.unapply)

  implicit lazy val publisherForm: Form[Publisher] =
    Form(implicitly[Mapping[Publisher]])

  implicit lazy val productIdFormatter: ProductIdFormatter =
    new ProductIdFormatter {}

  implicit lazy val productMapping: Mapping[Product] = mapping(
    "identity" -> optional(of[ProductId](productIdFormatter)),
    "name" -> text,
    "publisher" -> implicitly[Mapping[Publisher]]
  )(Product.apply)(Product.unapply)

  implicit lazy val productForm: Form[product.Product] =
    Form(implicitly[Mapping[product.Product]])
}

package com.example.manything.ancientail.outsiders.play

import com.example.manything.ambientendre.domain.publisher.Publisher
import com.example.manything.ancientail.domain.product.{Product, ProductId}
import play.api.data.{Form, Mapping}
import play.api.data.Forms._

package object forms {
  import com.example.manything.ambientendre.outsiders.play.forms.publisherMapping

  implicit lazy val productIdFormatter: ProductIdFormatter =
    new ProductIdFormatter {}

  implicit lazy val productMapping: Mapping[Product] = mapping(
    "identity" -> optional(of[ProductId](productIdFormatter)),
    "name" -> text,
    "publisher" -> implicitly[Mapping[Publisher]]
  )(Product.apply)(Product.unapply)

  implicit lazy val productForm: Form[Product] =
    Form(implicitly[Mapping[Product]])
}

package com.example.manything.ambientendre.outsiders.play.controllers.api.v1

import java.util.UUID

import play.api.mvc.QueryStringBindable

import com.example.manything.ambientendre.domain.models.product.ProductId

package object product {
  implicit def productIdBinder(
      implicit uuidBinder: QueryStringBindable[UUID]
  ): QueryStringBindable[ProductId] =
    new QueryStringBindable[ProductId] {
      override def bind(
          key: String,
          params: Map[String, Seq[String]]
      ): Option[Either[String, ProductId]] =
        uuidBinder.bind(key, params).map(_.map(ProductId.apply))
      override def unbind(key: String, value: ProductId): String =
        uuidBinder.unbind(key, value.value)
    }
}

package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ambientendre.domain.product.ProductId
import slick.jdbc.PostgresProfile.api._
import slick.lifted

package object product {
  lazy val products = lifted.TableQuery[Products]

  implicit lazy val productIdColumnType: BaseColumnType[ProductId] =
    MappedColumnType
      .base[ProductId, UUID](_.value, ProductId.apply)
}

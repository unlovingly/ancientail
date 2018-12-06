package com.example.manything.ambientendre.outsiders.slick

import java.util.UUID

import slick.lifted

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

package object product {
  lazy val products = lifted.TableQuery[Products]

  implicit lazy val productIdColumnType: BaseColumnType[ProductId] =
    MappedColumnType
      .base[ProductId, UUID](_.value, ProductId.apply)
}

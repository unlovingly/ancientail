package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ancientail.domain.product.{Product, ProductId}
import com.example.manything.roundelayout.domain.Identifiability
import slick.lifted
import slick.jdbc.PostgresProfile.api._

package object product {
  lazy val products = lifted.TableQuery[Products]

  implicit lazy val productIdColumnType: BaseColumnType[ProductId] =
    MappedColumnType
      .base[ProductId, UUID](_.value, Identifiability[UUID, Product])
}

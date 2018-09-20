package com.example.manything.ancientail.domain

import java.util.UUID

import com.example.manything.roundelayout.domain.Identifiability
import slick.jdbc.PostgresProfile.api._

package object product {
  type ProductId = Identifiability[UUID, product.Product]

  implicit val idColumnType: BaseColumnType[ProductId] =
    MappedColumnType
      .base[ProductId, UUID](_.value, Identifiability[UUID, product.Product])
}

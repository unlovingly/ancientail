package com.example.manything.ambientendre.outsiders.infrastructure
import java.util.UUID

import com.example.manything.ambientendre.domain.product.{
  ProductId,
  Product => DProduct
}
import com.example.manything.roundelayout.domain.Identifiability
import slick.lifted
import slick.jdbc.PostgresProfile.api._

package object product {
  lazy val products = lifted.TableQuery[Products]

  implicit lazy val productIdColumnType: BaseColumnType[ProductId] =
    MappedColumnType
      .base[ProductId, UUID](_.value, Identifiability[UUID, DProduct])
}

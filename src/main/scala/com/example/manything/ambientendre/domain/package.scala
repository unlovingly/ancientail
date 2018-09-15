package com.example.manything.ambientendre

import java.util.UUID

import com.example.manything.roundelayout.domain.Identifiability
import slick.jdbc.PostgresProfile.api._

package object domain {
  type ProductId = Identifiability[UUID, Product]

  implicit val idColumnType: BaseColumnType[ProductId] =
    MappedColumnType.base[ProductId, UUID](_.value, Identifiability[UUID, Product])
}

package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ancientail.domain.shop.{Shop, ShopId}
import com.example.manything.roundelayout.domain.Identifiability
import slick.lifted
import slick.jdbc.PostgresProfile.api._

package object shop {
  lazy val products = lifted.TableQuery[Shops]

  implicit lazy val shopIdColumnType: BaseColumnType[ShopId] =
    MappedColumnType
      .base[ShopId, UUID](_.value, Identifiability[UUID, Shop])
}

package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ancientail.domain.shop.{Shop => Entity, ShopId}
import com.example.manything.roundelayout.domain.Identifiability
import slick.lifted
import slick.jdbc.PostgresProfile.api._

package object shop {
  lazy val shops = lifted.TableQuery[Shops]
  lazy val stocks = lifted.TableQuery[Stocks]

  implicit lazy val shopIdColumnType: BaseColumnType[ShopId] =
    MappedColumnType
      .base[ShopId, UUID](_.value, Identifiability[UUID, Entity])
}

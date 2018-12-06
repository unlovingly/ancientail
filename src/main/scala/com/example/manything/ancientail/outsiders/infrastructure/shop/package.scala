package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import slick.lifted

import com.example.manything.ancientail.domain.models.shop.{PluCode, ShopId}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

package object shop {
  lazy val shops = lifted.TableQuery[Shops]
  lazy val stocks = lifted.TableQuery[Stocks]

  implicit lazy val pluCodeColumnType: BaseColumnType[PluCode] =
    MappedColumnType
      .base[PluCode, String](_.value, PluCode.apply)

  implicit lazy val shopIdColumnType: BaseColumnType[ShopId] =
    MappedColumnType
      .base[ShopId, UUID](_.value, ShopId.apply)
}

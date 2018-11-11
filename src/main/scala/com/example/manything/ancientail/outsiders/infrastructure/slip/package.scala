package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import slick.jdbc.PostgresProfile.api._
import slick.lifted

import com.example.manything.ancientail.domain.slip.{SlipId, SlipItemId}
import com.example.manything.ancientail.outsiders.infrastructure.slip.exchange.ExchangeSlips
import com.example.manything.ancientail.outsiders.infrastructure.slip.purchase.PurchaseSlips
import com.example.manything.ancientail.outsiders.infrastructure.slip.sales.SalesSlips

package object slip {
  implicit lazy val exchangeSlips: lifted.TableQuery[ExchangeSlips] =
    lifted.TableQuery[ExchangeSlips]

  implicit lazy val purchaseSlips: lifted.TableQuery[PurchaseSlips] =
    lifted.TableQuery[PurchaseSlips]

  implicit lazy val salesSlips: lifted.TableQuery[SalesSlips] =
    lifted.TableQuery[SalesSlips]

  lazy val slipItems = lifted.TableQuery[SlipItems]

  implicit lazy val slipIdColumnType: BaseColumnType[SlipId] =
    MappedColumnType
      .base[SlipId, UUID](_.value, SlipId.apply)

  implicit lazy val slipItemIdColumnType: BaseColumnType[SlipItemId] =
    MappedColumnType
      .base[SlipItemId, UUID](_.value, SlipItemId.apply)
}

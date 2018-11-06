package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ancientail.domain.slip.{SlipId, SlipItemId}
import slick.jdbc.PostgresProfile.api._
import slick.lifted

package object slip {
  lazy val slips = lifted.TableQuery[Slips]
  lazy val slipItems = lifted.TableQuery[SlipItems]

  implicit lazy val slipIdColumnType: BaseColumnType[SlipId] =
    MappedColumnType
      .base[SlipId, UUID](_.value, SlipId.apply)

  implicit lazy val slipItemIdColumnType: BaseColumnType[SlipItemId] =
    MappedColumnType
      .base[SlipItemId, UUID](_.value, SlipItemId.apply)
}

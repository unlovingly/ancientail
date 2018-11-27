package com.example.manything.ancientail.outsiders.infrastructure

import java.util.UUID

import slick.lifted

import com.example.manything.ancientail.domain.slip.{SlipId, SlipItemId}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

package object slip {
  implicit lazy val slipIdColumnType: BaseColumnType[SlipId] =
    MappedColumnType
      .base[SlipId, UUID](_.value, SlipId.apply)

  implicit lazy val slipItemIdColumnType: BaseColumnType[SlipItemId] =
    MappedColumnType
      .base[SlipItemId, UUID](_.value, SlipItemId.apply)
}

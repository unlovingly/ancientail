package com.example.manything.ambientendre.domain

import java.util.UUID

import com.example.manything.roundelayout.domain.Identifiability
import slick.jdbc.PostgresProfile.api._

package object publisher {
  type PublisherId = Identifiability[UUID, Publisher]

  implicit val idColumnType: BaseColumnType[PublisherId] =
    MappedColumnType.base[PublisherId, UUID](_.value, Identifiability[UUID, Publisher])
}

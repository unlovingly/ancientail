package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.Publisher
import com.example.manything.roundelayout.domain.Identifiability
import slick.ast.TypedType
import slick.lifted
import slick.jdbc.PostgresProfile.api._

package object publisher {
  val publishers = lifted.TableQuery[Publishers]

  implicit val idColumnType: TypedType[Identifiability[UUID, Publisher]] =
    MappedColumnType.base[Identifiability[UUID, Publisher], UUID](
      _.value,
      Identifiability[UUID, Publisher])
}

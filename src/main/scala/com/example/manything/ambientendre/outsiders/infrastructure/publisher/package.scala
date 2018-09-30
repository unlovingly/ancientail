package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.{
  Publisher => DPublisher,
  PublisherId
}
import com.example.manything.roundelayout.domain.Identifiability
import slick.lifted
import slick.jdbc.PostgresProfile.api._

package object publisher {
  lazy val publishers = lifted.TableQuery[Publishers]

  implicit lazy val publisherIdColumnType: BaseColumnType[PublisherId] =
    MappedColumnType
      .base[PublisherId, UUID](_.value, Identifiability[UUID, DPublisher])
}

package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.{
  PublisherId,
  Publisher => DPublisher
}
import com.example.manything.roundelayout.domain.Identifiability
import slick.jdbc.PostgresProfile.api._
import slick.lifted

package object publisher {
  lazy val publishers = lifted.TableQuery[Publishers]

  implicit lazy val publisherIdColumnType: BaseColumnType[PublisherId] =
    MappedColumnType
      .base[PublisherId, UUID](_.value, Identifiability[UUID, DPublisher])
}

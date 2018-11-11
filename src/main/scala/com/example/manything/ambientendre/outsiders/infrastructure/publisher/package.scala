package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import slick.jdbc.PostgresProfile.api._
import slick.lifted

import com.example.manything.ambientendre.domain.publisher.PublisherId

package object publisher {
  lazy val publishers = lifted.TableQuery[Publishers]

  implicit lazy val publisherIdColumnType: BaseColumnType[PublisherId] =
    MappedColumnType
      .base[PublisherId, UUID](_.value, PublisherId.apply)
}

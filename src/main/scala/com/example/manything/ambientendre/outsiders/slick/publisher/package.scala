package com.example.manything.ambientendre.outsiders.slick

import java.util.UUID

import slick.lifted

import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

package object publisher {
  lazy val publishers = lifted.TableQuery[Publishers]

  implicit lazy val publisherIdColumnType: BaseColumnType[PublisherId] =
    MappedColumnType
      .base[PublisherId, UUID](_.value, PublisherId.apply)
}

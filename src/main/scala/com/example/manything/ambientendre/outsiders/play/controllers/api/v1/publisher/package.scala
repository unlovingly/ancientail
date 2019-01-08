package com.example.manything.ambientendre.outsiders.play.controllers.api.v1

import java.util.UUID

import play.api.mvc.QueryStringBindable

import com.example.manything.ambientendre.domain.models.publisher.PublisherId

package object publisher {
  implicit def publisherIdBinder(
      implicit uuidBinder: QueryStringBindable[UUID]
  ): QueryStringBindable[PublisherId] =
    new QueryStringBindable[PublisherId] {
      override def bind(
          key: String,
          params: Map[String, Seq[String]]
      ): Option[Either[String, PublisherId]] =
        uuidBinder.bind(key, params).map(_.map(PublisherId.apply))
      override def unbind(key: String, value: PublisherId): String =
        uuidBinder.unbind(key, value.value)
    }
}

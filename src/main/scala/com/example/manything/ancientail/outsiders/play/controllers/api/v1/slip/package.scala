package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import play.api.mvc.PathBindable

import com.example.manything.ancientail.domain.slip.SlipId

package object slip {
  implicit def pathBinder(
    implicit uuidBinder: PathBindable[UUID]): PathBindable[SlipId] =
    new PathBindable[SlipId] {
      override def bind(key: String, value: String): Either[String, SlipId] =
        uuidBinder.bind(key, value).map(SlipId.apply)
      override def unbind(key: String, value: SlipId): String =
        value.value.toString
    }
}

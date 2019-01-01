package com.example.manything.ancientail.outsiders.play.controllers.api.v1

import java.util.UUID

import play.api.mvc.PathBindable

import com.example.manything.ancientail.domain.models.shop.{PluCode, ShopId}

package object shop {
  implicit def pluCodeBinder(
    implicit stringBinder: PathBindable[String]): PathBindable[PluCode] =
    new PathBindable[PluCode] {
      override def bind(key: String, value: String): Either[String, PluCode] =
        stringBinder.bind(key, value).map(PluCode.parse)
      override def unbind(key: String, value: PluCode): String =
        value.toString
    }
  implicit def shopIdBinder(
    implicit uuidBinder: PathBindable[UUID]): PathBindable[ShopId] =
    new PathBindable[ShopId] {
      override def bind(key: String, value: String): Either[String, ShopId] =
        uuidBinder.bind(key, value).map(ShopId.apply)
      override def unbind(key: String, value: ShopId): String =
        value.value.toString
    }
}

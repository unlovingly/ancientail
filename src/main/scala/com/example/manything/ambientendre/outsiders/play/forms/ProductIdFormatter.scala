package com.example.manything.ambientendre.outsiders.play.forms

import java.util.UUID

import com.example.manything.ambientendre.domain.product.{Product, ProductId}
import com.example.manything.roundelayout.domain.Identifiability
import play.api.data.FormError
import play.api.data.format.Formats.parsing
import play.api.data.format.Formatter

trait ProductIdFormatter extends Formatter[ProductId] {
  val i: String => ProductId =
    (id: String) => Identifiability[UUID, Product](UUID.fromString(id))

  override def bind(
    key: String,
    data: Map[String, String]): Either[Seq[FormError], ProductId] = {
    parsing(i, "error.id", Nil)(key, data)
  }

  override def unbind(key: String, value: ProductId): Map[String, String] = {
    Map(key -> value.value.toString)
  }
}

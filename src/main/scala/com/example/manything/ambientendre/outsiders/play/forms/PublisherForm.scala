package com.example.manything.ambientendre.outsiders.play.forms

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.roundelayout.domain.Identifiability
import play.api.data.Forms._
import play.api.data.{Form, FormError}
import play.api.data.format.Formatter
import play.api.data.format.Formats._

trait PublisherForm {
  val i =
    (id: String) => Identifiability[UUID, Publisher](UUID.fromString(id))

  val formatter = new Formatter[PublisherId] {
    override def bind(
      key: String,
      data: Map[String, String]): Either[Seq[FormError], PublisherId] = {
      parsing(i, "error.id", Nil)(key, data)
    }
    override def unbind(key: String,
                        value: PublisherId): Map[String, String] = {
      Map(key -> value.value.toString)
    }
  }

  val f = Form(
    mapping(
      "identity" -> optional(of[PublisherId](formatter)),
      "name" -> nonEmptyText
    )(Publisher.apply)(Publisher.unapply))
}

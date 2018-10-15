package com.example.manything.ambientendre.outsiders.play

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import play.api.data.{Form, Mapping}
import play.api.data.Forms._

package object forms {
  implicit lazy val publisherIdFormatter: PublisherIdFormatter =
    new PublisherIdFormatter {}

  implicit lazy val publisherMapping: Mapping[Publisher] = mapping(
    "identity" -> optional(of[PublisherId](publisherIdFormatter)),
    "name" -> text
  )(Publisher.apply)(Publisher.unapply)

  implicit lazy val publisherForm: Form[Publisher] =
    Form(implicitly[Mapping[Publisher]])
}

package com.example.manything.ambientendre.domain.publisher

import java.util.UUID

import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class Publisher(
  override val identity: Option[PublisherId] = None,
  name: String
) extends Entity {
  override type Identifier = PublisherId
}

case class PublisherId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = Publisher
}

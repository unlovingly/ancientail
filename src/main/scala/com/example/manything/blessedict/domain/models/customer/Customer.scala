package com.example.manything.blessedict.domain.models.customer

import java.util.UUID

import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class Customer(
  override val identity: Option[CustomerId],
  name: String
) extends Entity {
  override type Identifier = CustomerId
}

case class CustomerId(override val value: UUID)
  extends Identifiability[Customer, UUID]
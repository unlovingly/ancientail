package com.example.manything.blessedict.domain.models.customer

import java.util.UUID

import com.example.manything.roundelayout.domain.{Entity, Identifiability}

/**
 * 顧客
 * 店舗に来る人
 *
 * @param identity Option[CustomerId]
 * @param name 個人名
 */
case class Customer(
  override val identity: Option[CustomerId],
  name: String
) extends Entity {
  override type Identifier = CustomerId
}

case class CustomerId(override val value: UUID)
  extends Identifiability[Customer, UUID]

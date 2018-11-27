package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait SlipRepository[SlipType <: Slip, A[_]] extends Repository[UUID, A] {
  override type EntityType = SlipType
  override type Identifier = SlipId

  def retrieve(): A[Seq[SlipType]]
  def retrieve(id: Identifier): A[SlipType]
  def store(entity: EntityType): A[SlipType]
}

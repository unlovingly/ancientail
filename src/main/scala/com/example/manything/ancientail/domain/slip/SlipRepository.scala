package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait SlipRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = Slip
  override type Identifier = SlipId
}

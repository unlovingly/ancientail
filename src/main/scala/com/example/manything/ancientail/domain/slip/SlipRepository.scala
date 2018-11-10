package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait SlipRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = SlipBase
  override type Identifier = SlipId
}

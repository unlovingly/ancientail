package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ExchangeSlipRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = ExchangeSlip
  override type Identifier = SlipId
}

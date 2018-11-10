package com.example.manything.ancientail.domain.slip.exchange

import java.util.UUID

import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.roundelayout.domain.Repository

trait ExchangeSlipRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = ExchangeSlip
  override type Identifier = SlipId
}

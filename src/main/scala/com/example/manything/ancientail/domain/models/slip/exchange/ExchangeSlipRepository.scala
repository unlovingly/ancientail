package com.example.manything.ancientail.domain.models.slip.exchange

import com.example.manything.ancientail.domain.models.slip.SlipRepository

trait ExchangeSlipRepository[A[_]] extends SlipRepository[ExchangeSlip, A] {
  override type EntityType = ExchangeSlip
}

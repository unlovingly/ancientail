package com.example.manything.ancientail.domain.slip.exchange

import com.example.manything.ancientail.domain.slip.SlipRepository

trait ExchangeSlipRepository[A[_]] extends SlipRepository[A] {
  override type EntityType = ExchangeSlip
}

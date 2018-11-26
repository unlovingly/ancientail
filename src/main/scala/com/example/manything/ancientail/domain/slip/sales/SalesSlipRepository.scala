package com.example.manything.ancientail.domain.slip.sales

import com.example.manything.ancientail.domain.slip.SlipRepository

trait SalesSlipRepository[A[_]] extends SlipRepository[SalesSlip, A] {
  override type EntityType = SalesSlip
}

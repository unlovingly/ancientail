package com.example.manything.ancientail.domain.models.slip.sales

import com.example.manything.ancientail.domain.models.slip.SlipRepository

trait SalesSlipRepository[A[_]] extends SlipRepository[SalesSlip, A] {
  override type EntityType = SalesSlip
}

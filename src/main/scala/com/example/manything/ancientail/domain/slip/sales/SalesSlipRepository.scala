package com.example.manything.ancientail.domain.slip.sales

import com.example.manything.ancientail.domain.slip.SlipRepository

trait SalesSlipRepository[A[_]] extends SlipRepository[A] {
  override type EntityType = SalesSlip
}

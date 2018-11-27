package com.example.manything.ancientail.domain.slip.purchase

import com.example.manything.ancientail.domain.slip.SlipRepository

trait PurchaseSlipRepository[A[_]] extends SlipRepository[PurchaseSlip, A] {
  override type EntityType = PurchaseSlip
}

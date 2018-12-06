package com.example.manything.ancientail.domain.models.slip.purchase

import com.example.manything.ancientail.domain.models.slip.SlipRepository

trait PurchaseSlipRepository[A[_]] extends SlipRepository[PurchaseSlip, A] {
  override type EntityType = PurchaseSlip
}

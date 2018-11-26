package com.example.manything.ancientail.domain.slip.purchase

import com.example.manything.ancientail.domain.slip.{SlipId, SlipRepository}

trait PurchaseSlipRepository[A[_]] extends SlipRepository[A] {
  override type EntityType = PurchaseSlip
}

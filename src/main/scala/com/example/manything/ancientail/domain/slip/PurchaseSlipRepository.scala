package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait PurchaseSlipRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = PurchaseSlip
  override type Identifier = SlipId
}

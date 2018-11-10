package com.example.manything.ancientail.domain.slip.sales

import java.util.UUID

import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.roundelayout.domain.Repository

trait SalesSlipRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = SalesSlip
  override type Identifier = SlipId
}

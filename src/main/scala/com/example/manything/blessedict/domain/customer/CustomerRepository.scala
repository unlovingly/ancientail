package com.example.manything.blessedict.domain.customer

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait CustomerRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = Customer
  override type Identifier = CustomerId
}

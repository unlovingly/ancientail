package com.example.manything.blessedict.domain.customer

import com.example.manything.roundelayout.domain.Repository

trait CustomerRepository[A[_]] extends Repository[Customer] {
  override type EntityType = Customer
  override type Identifier = CustomerId
}

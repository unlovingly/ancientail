package com.example.manything.blessedict.domain.models.customer

import com.example.manything.roundelayout.domain.Repository

trait CustomerRepository[A[_]] extends Repository[Customer] {
  override type EntityType = Customer
  override type Identifier = CustomerId

  def retrieve(): A[Seq[EntityType]]
  def retrieve(id: Identifier): A[EntityType]
  def store(entity: EntityType): A[EntityType]
}

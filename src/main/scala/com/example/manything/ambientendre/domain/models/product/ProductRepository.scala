package com.example.manything.ambientendre.domain.models.product

import com.example.manything.roundelayout.domain.Repository

trait ProductRepository[A[_]] extends Repository[Product] {
  override type EntityType = Product
  override type Identifier = ProductId

  def retrieve(): A[Seq[EntityType]]
  def retrieve(id: Identifier): A[EntityType]
  def store(entity: EntityType): A[EntityType]
}
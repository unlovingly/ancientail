package com.example.manything.ambientendre.domain.publisher

import com.example.manything.roundelayout.domain.Repository

trait PublisherRepository[A[_]] extends Repository[Publisher] {
  override type EntityType = Publisher
  override type Identifier = PublisherId

  def retrieve(): A[Seq[EntityType]]
  def retrieve(id: Identifier): A[EntityType]
  def store(entity: EntityType): A[EntityType]
}

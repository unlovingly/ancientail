package com.example.manything.ambientendre.domain.models.publisher

import com.example.manything.roundelayout.domain.Repository

trait PublisherRepository[A[_]] extends Repository[Publisher] {
  override type EntityType = Publisher
  override type Identifier = PublisherId

  /**
   * 登録されている販売者を取得する
   */
  def retrieve(): A[Seq[EntityType]]

  /**
   * 登録されている販売者のうち指定されたものを取得する
   */
  def retrieve(id: Identifier): A[EntityType]

  /**
   * 登録されている販売者のうち指定されたものを取得する
   */
  def retrieve(id: Seq[Identifier]): A[Seq[EntityType]]

  /**
   * 販売者を新たに登録する
   */
  def store(entity: EntityType): A[EntityType]
}

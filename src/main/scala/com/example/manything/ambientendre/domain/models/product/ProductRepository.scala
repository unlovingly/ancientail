package com.example.manything.ambientendre.domain.models.product

import com.example.manything.roundelayout.domain.Repository

trait ProductRepository[A[_]] extends Repository[Product] {
  override type EntityType = Product
  override type Identifier = ProductId

  /**
   * 登録されている製品を取得する
   */
  def retrieve(): A[Seq[EntityType]]

  /**
   * 登録されている製品のうち指定されたものを取得する
   */
  def retrieve(id: Identifier): A[EntityType]

  /**
   * 登録されている製品のうち指定されたものを取得する
   */
  def retrieve(id: Seq[Identifier]): A[Seq[EntityType]]

  /**
   * 製品を新たに登録する
   */
  def store(entity: EntityType): A[EntityType]
}

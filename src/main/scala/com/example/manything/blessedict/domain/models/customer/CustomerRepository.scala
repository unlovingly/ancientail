package com.example.manything.blessedict.domain.models.customer

import com.example.manything.roundelayout.domain.Repository

trait CustomerRepository[A[_]] extends Repository[Customer] {
  override type EntityType = Customer
  override type Identifier = CustomerId

  /**
   * 登録されている顧客を取得する
   */
  def retrieve(): A[Seq[EntityType]]

  /**
   * 登録されている顧客のうち指定されたものを取得する
   */
  def retrieve(id: Identifier): A[EntityType]

  /**
   * 顧客を新たに登録する
   */
  def store(entity: EntityType): A[EntityType]
}

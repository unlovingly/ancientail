package com.example.manything.ancientail.domain.models.slip

import com.example.manything.roundelayout.domain.Repository

trait SlipRepository[SlipType <: Slip, A[_]] extends Repository[Slip] {
  override type EntityType = SlipType
  override type Identifier = SlipId

  /**
   * 伝票を全県取得する
   */
  def retrieve(): A[Seq[SlipType]]

  /**
   * 指定された伝票を取得する
   */
  def retrieve(id: Identifier): A[SlipType]

  /**
   * 伝票を永続化
   */
  def store(entity: EntityType): A[SlipType]
}

package com.example.manything.ancientail.domain.models.slip.sales

import com.example.manything.ancientail.domain.models.slip.SlipRepository

trait SalesSlipRepository[A[_]] extends SlipRepository[SalesSlip, A] {
  override type EntityType = SalesSlip

  /**
   * 売上を複数登録する
   * SlipRepository でもいいが他の伝票はできなくてもいい
   * @see store(entity: EntityType): A[SlipType]
   */
  def store(entity: Seq[EntityType]): A[Seq[SalesSlip]]
}

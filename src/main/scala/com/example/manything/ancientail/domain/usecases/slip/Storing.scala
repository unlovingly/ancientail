package com.example.manything.ancientail.domain.usecases.slip

import cats.MonadError

import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip

trait Storing[A[_]] {

  /**
   * 入庫処理
   */
  def store(slip: PurchaseSlip)(
      implicit ME: MonadError[A, Throwable]
  ): A[PurchaseSlip]
}

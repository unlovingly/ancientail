package com.example.manything.ancientail.domain.usecases.slip

import cats.MonadError

import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip

/**
 * 入庫ユースケース
 */
trait StoringProducts[A[_]] {

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def storing(slip: PurchaseSlip)(
    implicit ME: MonadError[A, Throwable]): A[PurchaseSlip]
}

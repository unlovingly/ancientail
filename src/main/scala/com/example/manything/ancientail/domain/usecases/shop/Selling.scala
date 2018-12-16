package com.example.manything.ancientail.domain.usecases.shop

import cats.MonadError

import com.example.manything.ancientail.domain.models.shop.Shop
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip

trait Selling[A[_]] {

  /**
   * 販売処理
   * @param slip
   * @return
   */
  def sell(slip: SalesSlip)(implicit ME: MonadError[A, Throwable]): A[Shop]
}

package com.example.manything.ancientail.domain.usecases.slip

import cats.MonadError

import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip

trait SellProducts[A[_]] {
  def sell(slip: SalesSlip)(implicit ME: MonadError[A, Throwable]): A[SalesSlip]
}

package com.example.manything.ancientail.domain.usecases.slip

import cats.Functor

import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip

trait SellProducts[A[_]] {
  def sell(slip: SalesSlip)(implicit F: Functor[A]): A[SalesSlip]
}

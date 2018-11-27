package com.example.manything.ancientail.usecases.slip

import com.example.manything.ancientail.domain.slip.sales.SalesSlip

trait SellProducts[A[_]] {
  def sell(slip: SalesSlip): A[SalesSlip]
}

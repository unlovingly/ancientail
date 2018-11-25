package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.sales.SalesSlip

/**
 * 詳細をみるときのユースケース
 */
trait SaleProducts { this: SlipUseCases =>
  def sell(slip: SalesSlip): EitherTFuture[SalesSlip] = ???
}

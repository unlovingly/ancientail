package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveSlips { this: SlipUseCases =>
  def retrieve(): EitherTFuture[Seq[PurchaseSlip]] = purchaseSlips.retrieve()
}

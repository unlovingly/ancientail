package com.example.manything.ancientail.domain.usecases.shop

import cats.MonadError

import com.example.manything.ancientail.domain.models.shop.Shop
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip

trait Storing[A[_]] {
  def store(slip: PurchaseSlip)(implicit ME: MonadError[A, Throwable]): A[Shop]
}

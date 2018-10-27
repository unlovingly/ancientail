package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[Shop, UUID, A] {
  def stock(shopId: ShopId, stocks: Seq[Stock]): A[Shop]
}

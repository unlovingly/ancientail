package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[Shop, UUID, A] {
  def retrieveWithStocks(shopId: ShopId, productId: Seq[ProductId]): A[Shop]
}

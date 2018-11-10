package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = Shop
  override type Identifier = ShopId

  def retrieveWithStocks(shopId: Identifier,
                         productId: Seq[ProductId]): A[EntityType]
}

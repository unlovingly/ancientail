package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[UUID, A] {
  override type EntityType = Shop
  override type Identifier = ShopId

  def retrieveWithStocks(shopId: Identifier,
                         productId: Seq[ProductId]): A[EntityType]

  def retrieveWithStocks(q: String): A[Seq[EntityType]]

  /**
   * 棚卸し処理、理論在庫状態を保存する
   *
   * @return
   */
  def inventory(copyTo: String): A[Unit]

  def retrieve(): A[Seq[EntityType]]
  def retrieve(id: Identifier): A[EntityType]
  def store(entity: EntityType): A[EntityType]
}

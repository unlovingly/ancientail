package com.example.manything.ancientail.domain.models.shop

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[Shop] {
  override type EntityType = Shop
  override type Identifier = ShopId

  def retrieveWithStocksBy(q: String): A[Seq[EntityType]]
  def retrieveWithStocksBy(shopId: Identifier): A[EntityType]
  def retrieveWithStocksBy(shopId: Identifier, code: PluCode): A[EntityType]
  def retrieveWithStocksBy(shopId: Identifier,
                           productId: Seq[ProductId]): A[EntityType]

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

package com.example.manything.ancientail.domain.models.shop

import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[Shop] {
  override type EntityType = Shop
  override type Identifier = ShopId

  /**
   * q を製品型番にもつ Stock を取得する
   */
  def retrieveWithStocksBy(q: String): A[Seq[EntityType]]

  /**
   * 指定された店舗を取得する
   */
  def retrieveWithStocksBy(shopId: Identifier): A[EntityType]

  /**
   * 指定された店舗がもつ在庫を取得する
   *
   * @deprecated
   * @see retrieveWithStocksBy(shopId: Identifier, codes: Seq[PluCode])
   */
  def retrieveWithStocksBy(shopId: Identifier, code: PluCode): A[EntityType]

  /**
   * 指定された店舗がもつ在庫を取得する
   */
  def retrieveWithStocksBy(
      shopId: Identifier,
      codes: Seq[PluCode]
  ): A[EntityType]

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

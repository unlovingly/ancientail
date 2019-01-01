package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.ambientendre.domain.models.product.{ Product, ProductId }

trait ShowProducts[A[_]] {

  /**
   * 登録されている製品を取得する
   */
  def retrieve(): A[Seq[Product]]

  /**
   * 登録されている製品から指定された製品を取得する
   */
  def retrieve(id: Seq[ProductId]): A[Seq[Product]]
}

package com.example.manything.ambientendre.domain.usecases.product

import com.example.manything.ambientendre.domain.models.product.Product

trait CreateProduct[A[_]] {

  /**
   * 製品をシステムに登録する
   */
  def create(p: Product): A[Product]
}

package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop.Shop

trait CreateShop[A[_]] {

  /**
   * 店舗をシステムに登録する
   */
  def create(s: Shop): A[Shop]
}

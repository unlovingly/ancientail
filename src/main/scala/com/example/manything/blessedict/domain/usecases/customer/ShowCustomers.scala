package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.blessedict.domain.models.customer.Customer

trait ShowCustomers[A[_]] {

  /**
   * 登録されている店舗を取得する
   */
  def retrieve(): A[Seq[Customer]]
}

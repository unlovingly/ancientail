package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.blessedict.domain.models.customer.Customer

trait CreateCustomer[A[_]] {

  /**
   * 顧客をシステムに登録する
   */
  def create(p: Customer): A[Customer]
}

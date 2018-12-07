package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.blessedict.domain.models.customer.Customer

trait CreateCustomer[A[_]] { this: CustomerUseCases[A] =>
  def create(p: Customer): A[Customer] = customers.store(p)
}

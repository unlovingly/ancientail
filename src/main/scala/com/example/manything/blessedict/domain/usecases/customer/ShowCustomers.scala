package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.blessedict.domain.models.customer.Customer

trait ShowCustomers[A[_]] { this: CustomerUseCases[A] =>
  def retrieve(): A[Seq[Customer]] = customers.retrieve()
}

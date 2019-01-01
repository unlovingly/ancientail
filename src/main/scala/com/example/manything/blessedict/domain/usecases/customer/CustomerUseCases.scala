package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.blessedict.domain.models.customer.{ Customer, CustomerRepository }

class CustomerUseCases[A[_]](val customers: CustomerRepository[A])
    extends CreateCustomer[A]
    with ShowCustomers[A] {
  override def create(p: Customer): A[Customer] = customers.store(p)
  override def retrieve(): A[Seq[Customer]] = customers.retrieve()
}

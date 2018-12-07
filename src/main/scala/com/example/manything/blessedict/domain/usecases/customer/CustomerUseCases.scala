package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.blessedict.domain.models.customer.CustomerRepository

class CustomerUseCases[A[_]](val customers: CustomerRepository[A])
  extends CreateCustomer[A]
  with ShowCustomers[A]

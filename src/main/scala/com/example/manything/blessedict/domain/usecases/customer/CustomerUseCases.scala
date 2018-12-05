package com.example.manything.blessedict.domain.usecases.customer

import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.customer.CustomerRepository

class CustomerUseCases(val customers: CustomerRepository[EitherTFuture])
  extends CreateCustomer
  with ShowCustomers

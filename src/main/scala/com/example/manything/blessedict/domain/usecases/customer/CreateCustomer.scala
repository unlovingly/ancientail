package com.example.manything.blessedict.domain.usecases.customer
import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.models.customer.Customer

trait CreateCustomer { this: CustomerUseCases =>
  def create(p: Customer): EitherTFuture[Customer] = customers.store(p)
}

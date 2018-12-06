package com.example.manything.blessedict.domain.usecases.customer
import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.models.customer.Customer

trait ShowCustomers { this: CustomerUseCases =>
  def retrieve(): EitherTFuture[Seq[Customer]] = customers.retrieve()
}

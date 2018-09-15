package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ambientendre.domain._
import com.example.manything.roundelayout.domain.Identifiability

import scala.concurrent.Future

class ProductRepositoryWithSlick extends ProductRepository {
  override def retrieve: Future[Seq[Product]] = ???

  override def retrieve(id: Identifiability[UUID, Product]): Future[Product] = ???

  override def store(entity: Product): Future[Product] = ???
}

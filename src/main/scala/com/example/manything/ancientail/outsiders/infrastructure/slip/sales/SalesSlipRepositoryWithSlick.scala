package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import scala.concurrent.ExecutionContext

import slick.jdbc.PostgresProfile.api._

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.sales.SalesSlipRepository

class SalesSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends SalesSlipRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???
  override def retrieve(id: Seq[Identifier]): EitherTFuture[Seq[EntityType]] =
    ???

  override def store(entity: EntityType): EitherTFuture[EntityType] = ???
}

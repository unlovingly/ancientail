package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import scala.concurrent.ExecutionContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlipRepository
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class PurchaseSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends PurchaseSlipRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???
  override def retrieve(id: Seq[Identifier]): EitherTFuture[Seq[EntityType]] =
    ???

  override def store(entity: EntityType): EitherTFuture[EntityType] = ???
}

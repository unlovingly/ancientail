package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import scala.concurrent.ExecutionContext

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.sales.SalesSlipRepository
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class SalesSlipRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends SalesSlipRepository[EitherTFuture] {
  protected val slips: lifted.TableQuery[SalesSlips] =
    lifted.TableQuery[SalesSlips]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???
  override def retrieve(id: Identifier): EitherTFuture[EntityType] =
    ???
  override def store(entity: EntityType): EitherTFuture[EntityType] = ???
}

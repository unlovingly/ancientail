package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import scala.concurrent.ExecutionContext

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.{SlipId, SlipItem}
import com.example.manything.ancientail.domain.slip.sales.SalesSlipRepository
import com.example.manything.ancientail.outsiders.infrastructure.slip.{
  SlipObject,
  SlipRepositoryWithSlick
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._
import com.example.manything.ancientail.domain.slip.sales.{SalesSlip => Entity}

class SalesSlipRepositoryWithSlick(
  implicit override val db: Database,
  implicit override val executionContext: ExecutionContext)
  extends SlipRepositoryWithSlick[SalesSlip]
  with SalesSlipRepository[EitherTFuture] {
  override type TableType = SalesSlips
  override type EntityType = Entity

  protected val companion: SlipObject[SalesSlip] =
    implicitly[SlipObject[SalesSlip]]
  protected val slips: lifted.TableQuery[SalesSlips] =
    lifted.TableQuery[SalesSlips]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???
  override def retrieve(id: Identifier): EitherTFuture[EntityType] =
    ???

  override def updateEntity(entity: Entity,
                            id: Option[SlipId],
                            items: Seq[SlipItem]): Entity = {
    Entity(identity = id,
           number = entity.number,
           senderId = entity.senderId,
           receiverId = entity.receiverId,
           items = items,
           publishedAt = entity.publishedAt)
  }
}

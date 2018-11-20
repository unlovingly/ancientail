package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.{SlipId, SlipItem}
import com.example.manything.ancientail.domain.slip.purchase.{
  PurchaseSlipRepository,
  PurchaseSlip => Entity
}
import com.example.manything.ancientail.outsiders.infrastructure.slip.{
  SlipObject,
  SlipRepositoryWithSlick
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class PurchaseSlipRepositoryWithSlick(
  implicit override val db: Database,
  implicit override val executionContext: ExecutionContext)
  extends SlipRepositoryWithSlick[PurchaseSlip]
  with PurchaseSlipRepository[EitherTFuture] {
  import com.example.manything.ancientail.outsiders.infrastructure.slip.purchase.PurchaseSlip.self

  override type TableType = PurchaseSlips
  override type EntityType = Entity

  protected val companion: SlipObject[PurchaseSlip] =
    implicitly[SlipObject[PurchaseSlip]]
  protected val slips: lifted.TableQuery[PurchaseSlips] =
    lifted.TableQuery[PurchaseSlips]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits._

    val q = slips.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map { _.map { _.to() } }
  }
  override def updateEntity(entity: Entity,
                            id: Option[SlipId],
                            items: Seq[SlipItem]): Entity = {
    Entity(identity = id,
           senderId = entity.senderId,
           receiverId = entity.receiverId,
           items = items,
           publishedAt = entity.publishedAt)
  }
}

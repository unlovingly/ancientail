package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipRepository,
  Slip => EntityBase,
  SlipItem => EntityItem
}
import com.example.manything.ancientail.outsiders.infrastructure.slip.{
  SlipItem,
  purchaseSlips,
  slipItems
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

class PurchaseSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends SlipRepository[PublisherId, EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityBase[PublisherId]]] = {
    val q = purchaseSlips.take(20)
    val a = q.result.asTry
      .map { _.toEither }
      .map {
        _.map {
          _.map { _.as() }
        }
      }

    db.run(a)
  }

  def retrieve(
    id: Seq[SlipId]): EitherAppliedFuture[Seq[EntityBase[PublisherId]]] = {
    val q = purchaseSlips
      .filter(_.identity.inSetBind(id))
      .take(20)
    val a = q.result.asTry
      .map { _.toEither }
      .map {
        _.map {
          _.map { _.as() }
        }
      }

    db.run(a)
  }

  def store(entity: EntityBase[PublisherId])
    : EitherAppliedFuture[EntityBase[PublisherId]] = {
    val queries = for {
      savedSlipId <- (purchaseSlips returning purchaseSlips.map { _.identity }) +=
        PurchaseSlip(entity.identity, entity.senderId, entity.receiverId)
      savedSlipItems <- (slipItems returning slipItems) ++=
        entity.items.map(SlipItem.from)
    } yield (savedSlipId, savedSlipItems)

    val actions = queries.asTry
      .map { _.toEither }
      .map {
        _.map {
          case (slipId, newSlipItems) =>
            entity.copy(
              identity = Some(slipId),
              items = newSlipItems.map { i =>
                EntityItem(i.identity, i.productId, i.amount, i.price)
              }
            )
        }
      }

    db.run(actions)
  }
}

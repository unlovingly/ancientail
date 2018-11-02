package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipRepository,
  Slip => Entity,
  SlipItem => EntityItem
}
import com.example.manything.ancientail.outsiders.infrastructure.slip.purchase.PurchaseSlip
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

class SlipRepositoryWithSlick(implicit val db: Database,
                              implicit val executionContext: ExecutionContext)
  extends SlipRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[Entity]] = {
    val q = purchaseSlips.take(20)
    val a = q.result.asTry
      .map { // FIXME
        _.toEither
          .map {
            _.map { p =>
              Entity(identity = p.identity,
                     senderId = p.senderId,
                     receiverId = p.receiverId,
                     items = Seq.empty) // TODO
            }
          }
      }

    db.run(a)
  }

  override def retrieve(id: Seq[SlipId]): EitherAppliedFuture[Seq[Entity]] = {
    val q = for {
      p <- purchaseSlips if p.identity.inSet(id)
    } yield p
    val a = q.result.asTry
      .map { // FIXME
        _.toEither
          .map {
            _.map { p =>
              Entity(identity = p.identity,
                     senderId = p.senderId,
                     receiverId = p.receiverId,
                     items = Seq.empty)
            }
          }
      }

    db.run(a)
  }

  override def store(entity: Entity): EitherAppliedFuture[Entity] = {
    val queries = for {
      savedSlipId <- (purchaseSlips returning purchaseSlips.map { _.identity }) += PurchaseSlip(
        entity.identity,
        entity.senderId,
        entity.receiverId)
      savedSlipItems <- (slipItems returning slipItems) ++= entity.items.map {
        e =>
          SlipItem(identity = e.identity,
                   productId = e.productId,
                   amount = e.amount,
                   price = e.price,
                   slipId = savedSlipId)
      }
    } yield (savedSlipId, savedSlipItems)

    val actions = queries.asTry.map { _.toEither }.map {
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

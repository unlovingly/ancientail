package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.{
  SlipRepository,
  Slip => Entity,
  SlipItem => EntityItem
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

class SlipRepositoryWithSlick(implicit val db: Database,
                              implicit val executionContext: ExecutionContext)
  extends SlipRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityType]] = {
    val q = slips.take(20)
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

  override def retrieve(
    id: Seq[Identifier]): EitherAppliedFuture[Seq[EntityType]] = {
    val q = for {
      p <- slips if p.identity.inSet(id)
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

  override def store(entity: EntityType): EitherAppliedFuture[EntityType] = {
    val queries = for {
      savedSlipId <- (slips returning slips.map { _.identity }) += Slip(
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

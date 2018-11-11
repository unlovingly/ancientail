package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import scala.concurrent.ExecutionContext

import slick.jdbc.PostgresProfile.api._
import slick.lifted

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.sales.SalesSlipRepository
import com.example.manything.ancientail.domain.slip.{SlipItem => EntityItem}

class SalesSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends SalesSlipRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityType]] = ???
  override def retrieve(
    id: Seq[Identifier]): EitherAppliedFuture[Seq[EntityType]] =
    ???

  override def store(entity: EntityType): EitherAppliedFuture[EntityType] = {
    import com.example.manything.ancientail.outsiders.infrastructure.slip._

    val slips = implicitly[lifted.TableQuery[SalesSlips]]

    val queries = for {
      savedSlipId <- (slips returning slips.map { _.identity }) += SalesSlip(
        identity = entity.identity,
        receiverId = entity.receiverId,
        senderId = entity.senderId,
        publishedAt = entity.publishedAt.toOffsetDateTime)
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

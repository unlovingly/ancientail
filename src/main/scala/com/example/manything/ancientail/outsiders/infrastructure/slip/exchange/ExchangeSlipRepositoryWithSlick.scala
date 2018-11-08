package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.{
  ExchangeSlipRepository,
  SlipItem => EntityItem
}
import slick.jdbc.PostgresProfile.api._
import slick.lifted

import scala.concurrent.ExecutionContext

class ExchangeSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ExchangeSlipRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityType]] = ???
  override def retrieve(
    id: Seq[Identifier]): EitherAppliedFuture[Seq[EntityType]] = ???

  override def store(entity: EntityType): EitherAppliedFuture[EntityType] = {
    import com.example.manything.ancientail.outsiders.infrastructure.slip._

    val slips = implicitly[lifted.TableQuery[ExchangeSlips]]

    val queries = for {
      savedSlipId <- (slips returning slips.map { _.identity }) += ExchangeSlip(
        identity = entity.identity,
        receiverId = entity.receiverId,
        senderId = entity.senderId)
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

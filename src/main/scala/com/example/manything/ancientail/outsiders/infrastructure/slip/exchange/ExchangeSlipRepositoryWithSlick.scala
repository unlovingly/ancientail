package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipItem => EntityItem
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class ExchangeSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ExchangeSlipRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???
  override def retrieve(id: Identifier): EitherTFuture[EntityType] =
    ???

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    import com.example.manything.ancientail.outsiders.infrastructure.slip._

    val slips = lifted.TableQuery[ExchangeSlips]

    val query = for {
      q1 <- (slips returning slips.map { _.identity }) += ExchangeSlip.from(
        entity)
      q2 <- store(q1, entity.items)
    } yield (q1, q2)

    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      case (id, items) =>
        val i: Seq[EntityItem] = items.flatten.map(_.to())

        entity.copy(identity = Some(id), items = i)
    }
  }

  private def store(slipId: SlipId, ss: Seq[EntityItem]) = {
    import com.example.manything.ancientail.outsiders.infrastructure.slip._

    val targets = ss.map { i =>
      SlipItem(identity = i.identity,
               productId = i.productId,
               amount = i.amount,
               price = i.price,
               slipId = slipId)
    }

    DBIO.sequence {
      targets.map { t =>
        (slipItems returning slipItems).insertOrUpdate(t)
      }
    }
  }
}

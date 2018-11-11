package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.jdbc.PostgresProfile.api._
import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipItem => EntityItem
}

class ExchangeSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends ExchangeSlipRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???
  override def retrieve(id: Seq[Identifier]): EitherTFuture[Seq[EntityType]] =
    ???

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    import com.example.manything.ancientail.outsiders.infrastructure.slip._

    val slips = implicitly[lifted.TableQuery[ExchangeSlips]]

    val q1 = (slips returning slips.map { _.identity }) += ExchangeSlip.from(
      entity)
    val q2 = q1.flatMap { id =>
      store(id, entity.items)

      q1
    }

    val a = q2.asTry.map { _.toEither }

    // items も更新しないと
    EitherT(db.run(a)).map { id =>
      entity.copy(identity = Some(id))
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

package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.{SlipId, SlipItem}
import com.example.manything.ancientail.outsiders.infrastructure.slip.PolishedSlipItem
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class ExchangeSlipRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends ExchangeSlipRepository[EitherTFuture] {
  val slips = lifted.TableQuery[ExchangeSlips]
  val slipItems = lifted.TableQuery[SlipItems]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits._

    val q = slips.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      _.map(_.to())
    }
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits._

    import com.example.manything.ancientail.outsiders.infrastructure.slip.slipIdColumnType

    val q = for {
      s <- slips if s.identity === id.bind
      i <- slipItems if i.slipId === s.identity
    } yield (s, i)
    val a = q.result.asTry.map { _.toEither }

    // FIXME
    val result = EitherT(db.run(a)).map { tuple =>
      tuple
        .groupBy(_._1)
        .mapValues(_.map(_._2))
        .map {
          case (slip, items) =>
            slip.to(items.map(_.to()))
        }
        .toSeq
        .head
    }

    result
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    import com.example.manything.ancientail.outsiders.infrastructure.slip.slipIdColumnType

    val query = for {
      q1 <- (slips returning slips.map { _.identity }) += PolishedExchangeSlip
        .from(entity)
      q2 <- store(q1, entity.items)
    } yield (q1, q2)

    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      case (id, items) =>
        val i: Seq[SlipItem] = items.flatten.map(_.to())

        entity.copy(identity = Some(id), items = i)
    }
  }

  private def store(slipId: SlipId, ss: Seq[SlipItem]) = {
    val targets = ss.map(PolishedSlipItem.from(slipId, _))

    DBIO.sequence {
      targets.map { t =>
        (slipItems returning slipItems).insertOrUpdate(t)
      }
    }
  }
}

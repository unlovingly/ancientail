package com.example.manything.ancientail.outsiders.slick.slip.exchange

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.exchange.{
  ExchangeSlipItem,
  ExchangeSlipRepository
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class ExchangeSlipRepositoryWithSlick(val db: Database)(
    implicit val executionContext: ExecutionContext
) extends ExchangeSlipRepository[EitherTFuture] {
  val slips = lifted.TableQuery[ExchangeSlips]
  val slipItems = lifted.TableQuery[SlipItems]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = slips.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(new NoSuchElementException())(_.nonEmpty)
      .map {
        _.map(_.to())
      }
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    import com.example.manything.ancientail.outsiders.slick.slip.slipIdColumnType

    val q = for {
      s <- slips if s.identity === id.bind
      i <- slipItems if i.slipId === s.identity
    } yield (s, i)
    val a = q.result.asTry.map { _.toEither }

    val result = EitherT(db.run(a))
      .ensure(new NoSuchElementException())(_.nonEmpty)
      .map {
        _.groupBy(_._1).map {
          case (slip, items) =>
            slip.to(items.map(_._2.to()))
        }.head
      }

    result
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    import com.example.manything.ancientail.outsiders.slick.slip.slipIdColumnType

    val query = for {
      q1 <- (slips returning slips.map { _.identity }) += PolishedExchangeSlip
        .from(entity)
      q2 <- store(q1, entity.items)
    } yield (q1, q2)

    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      case (id, items) =>
        val i = items.flatten.map(_.to())

        entity.copy(identity = Some(id), items = i)
    }
  }

  private def store(slipId: SlipId, ss: Seq[ExchangeSlipItem]) = {
    val targets = ss.map(PolishedExchangeSlipItem.from(slipId, _))

    DBIO.sequence {
      targets.map { t =>
        (slipItems returning slipItems).insertOrUpdate(t)
      }
    }
  }
}

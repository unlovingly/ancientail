package com.example.manything.ancientail.outsiders.slick.slip.sales

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.sales._
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class SalesSlipRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends SalesSlipRepository[EitherTFuture] {
  val slips = lifted.TableQuery[SalesSlips]
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
      .map { tuple =>
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
    require(entity.items.nonEmpty)

    val query = storeSlip(entity)
    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a))
  }

  override def store(
    entities: Seq[SalesSlip]): EitherTFuture[Seq[SalesSlip]] = {
    require(entities.nonEmpty)

    val query = storeSlips(entities)
    val action = query.asTry.map { _.toEither }

    EitherT(db.run(action))
  }

  private def storeSlip(entity: SalesSlip) = {
    val target = PolishedSalesSlip.from(entity)
    val query =
      entity.identity match {
        case Some(_) => slips.update(target).map(_ => target)
        case _ => (slips returning slips).+=(target)
      }

    val action = for {
      slip <- query
      items <- storeItems(slip.identity.get, entity.items)
    } yield (slip, items)

    action.map {
      case (e, i) =>
        e.to(items = i.map(_.to()))
    }
  }

  private def storeSlips(entities: Seq[SalesSlip]) = {
    DBIO.sequence {
      entities.map { s =>
        storeSlip(s)
      }
    }
  }

  private def storeItem(entity: PolishedSalesSlipItem) = {
    if (entity.identity.isDefined) {
      slipItems.update(entity).map(_ => entity)
    } else {
      (slipItems returning slipItems).+=(entity)
    }
  }

  private def storeItems(slipId: SlipId, ss: Seq[SalesSlipItem]) = {
    DBIO.sequence {
      ss.map { s =>
        storeItem(PolishedSalesSlipItem.from(slipId, s))
      }
    }
  }
}

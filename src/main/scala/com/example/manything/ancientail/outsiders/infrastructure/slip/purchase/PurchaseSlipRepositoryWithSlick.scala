package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlipRepository
import com.example.manything.ancientail.domain.slip.{SlipId, SlipItem}
import com.example.manything.ancientail.outsiders.infrastructure.slip.PolishedSlipItem
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class PurchaseSlipRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends PurchaseSlipRepository[EitherTFuture] {
  val slips = lifted.TableQuery[PurchaseSlips]
  val slipItems = lifted.TableQuery[SlipItems]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = slips.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      _.map(_.to())
    }
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

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
    import cats.implicits.catsStdInstancesForFuture

    val query = for {
      q1 <- storeSlip(PolishedPurchaseSlip.from(entity))
      q2 <- storeItems(q1, entity.items)
    } yield (q1, q2)

    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      case (id, items) =>
        val i = items.map(_.to())

        entity.copy(identity = Some(id), items = i)
    }
  }

  private def storeSlip(entity: PolishedPurchaseSlip) = {
    import com.example.manything.ancientail.outsiders.infrastructure.slip.slipIdColumnType

    if (entity.identity.isDefined) {
      slips.update(entity).map(_ => entity.identity.get)
    } else {
      (slips returning slips.map { _.identity }).+=(entity)
    }
  }

  private def storeItems(slipId: SlipId, ss: Seq[SlipItem]) = {
    def storeItems(entity: PolishedSlipItem) = {
      if (entity.identity.isDefined) {
        slipItems.update(entity).map(_ => entity)
      } else {
        (slipItems returning slipItems).+=(entity)
      }
    }

    DBIO.sequence {
      ss.map { s =>
        storeItems(PolishedSlipItem.from(slipId, s))
      }
    }
  }
}

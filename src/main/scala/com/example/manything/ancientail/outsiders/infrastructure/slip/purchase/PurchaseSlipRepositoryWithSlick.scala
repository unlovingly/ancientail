package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.SlipItem
import com.example.manything.ancientail.domain.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipRepository
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class PurchaseSlipRepositoryWithSlick(
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends PurchaseSlipRepository[EitherTFuture] {
  protected val slips: lifted.TableQuery[PurchaseSlips] =
    lifted.TableQuery[PurchaseSlips]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits._

    val q = slips.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      _.map { s =>
        PurchaseSlip(
          identity = s.identity,
          number = s.number,
          senderId = s.senderId,
          receiverId = s.receiverId,
          publishedAt = s.publishedAt.toZonedDateTime,
          approvedAt = s.approvedAt.toZonedDateTime,
          items = Seq.empty
        )
      }
    }
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits._

    import com.example.manything.ancientail.outsiders.infrastructure.slip.{
      slipIdColumnType,
      slipItems
    }

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
            PurchaseSlip(
              identity = slip.identity,
              number = slip.number,
              senderId = slip.senderId,
              receiverId = slip.receiverId,
              publishedAt = slip.publishedAt.toZonedDateTime,
              approvedAt = slip.approvedAt.toZonedDateTime,
              items = items.map { i =>
                SlipItem(
                  identity = i.identity,
                  productId = i.productId,
                  amount = i.amount,
                  price = i.price
                )
              }
            )
        }
        .toSeq
        .head
    }

    result
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = ???
}

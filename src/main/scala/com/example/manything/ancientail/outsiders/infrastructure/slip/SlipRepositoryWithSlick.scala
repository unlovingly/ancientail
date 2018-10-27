package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipItem,
  SlipRepository,
  Slip => Entity
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class SlipRepositoryWithSlick(implicit val db: Database,
                              implicit val executionContext: ExecutionContext)
  extends SlipRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[Entity]] = {
    val q = slips.take(20)
    val a = q.result.asTry
      .map { // FIXME
        _.toEither
          .map {
            _.map { p =>
              Entity(identity = p.identity,
                     senderId = p.senderId,
                     receiverId = p.receiverId,
                     items = Seq.empty[SlipItem]) // TODO
            }
          }
      }

    db.run(a)
  }

  override def retrieve(id: Seq[SlipId]): EitherAppliedFuture[Seq[Entity]] = {
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
                     items = Seq.empty[SlipItem])
            }
          }
      }

    db.run(a)
  }

  override def store(entity: Entity): EitherAppliedFuture[Entity] = {
    val q1 = (slips returning slips.map { _.identity }) += Slip(
      entity.identity,
      entity.senderId,
      entity.receiverId)
    val a1 = q1.asTry
      .map {
        case Success(id) => Right(entity.copy(identity = Some(id)))
        case Failure(e) => Left(e)
      }

    val q2 = (slipItems returning slipItems.map { _.identity }) ++= entity.items
      .map { e =>
        SlipItem(identity = None,
                 productId = e.productId,
                 amount = e.amount,
                 price = e.price)
      }
    val a2 = q2.asTry

    val q3 = (slips returning slips.map { _.identity }) += Slip(
      entity.identity,
      entity.senderId,
      entity.receiverId)
    val q4 = (slipItems returning slipItems.map { _.identity }) ++= entity.items

    val queries = for {
      slips <- q3
      slipItems <- q4
    } yield (slips, slipItems)

    val actions = DBIO.seq(a1, a2)

    db.run(a1)
  }
}

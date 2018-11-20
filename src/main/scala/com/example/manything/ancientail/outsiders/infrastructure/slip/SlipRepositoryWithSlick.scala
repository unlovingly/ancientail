package com.example.manything.ancientail.outsiders.infrastructure.slip

import scala.concurrent.{ExecutionContext, Future}

import cats.data.EitherT

import slick.lifted

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipRepository,
  SlipItem => EntityItem
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

abstract class SlipRepositoryWithSlick[A <: SlipBase](
  implicit val db: Database,
  implicit val executionContext: ExecutionContext)
  extends SlipRepository[EitherTFuture] {
  type TableType <: SlipsBase[A]

  protected val companion: SlipObject[A]
  protected val slips: lifted.TableQuery[TableType]

  override def retrieve(): EitherTFuture[Seq[EntityType]] = ???

  override def retrieve(id: Seq[Identifier]): EitherTFuture[Seq[EntityType]] =
    ???

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    val query = for {
      q1 <- (slips returning slips.map { _.identity }) += companion.from(entity)
      q2 <- store(q1, entity.items)
    } yield (q1, q2)

    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      case (id, items) => {
        val i: Seq[EntityItem] = items.flatten.map(_.to())

        updateEntity(entity, Some(id), i)
      }
    }
  }

  def updateEntity(entity: EntityType,
                   id: Option[SlipId],
                   items: Seq[EntityItem]): EntityType

  private def store(slipId: SlipId, ss: Seq[EntityItem]) = {

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

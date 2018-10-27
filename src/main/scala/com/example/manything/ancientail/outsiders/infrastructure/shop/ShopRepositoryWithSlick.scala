package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.{
  ShopId,
  ShopRepository,
  Stock,
  Shop => Entity
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class ShopRepositoryWithSlick(implicit val db: Database,
                              implicit val executionContext: ExecutionContext)
  extends ShopRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[Entity]] = {
    val q = shops.take(20)
    val a = q.result.asTry.map { _.toEither }

    // TODO:
    // https://stackoverflow.com/a/52623098/1764794
    // https://stackoverflow.com/a/31285681/1764794
    db.run(a).map {
      _.map {
        _.map { s =>
          Entity(s.identity, s.name, Seq.empty[Stock])
        }
      }
    }
  }

  override def retrieve(id: Seq[ShopId]): EitherAppliedFuture[Seq[Entity]] = {
    val q = for {
      p <- shops if p.identity.inSet(id)
    } yield p
    val a = q.result.asTry.map { _.toEither }

    db.run(a).map {
      _.map {
        _.map { s =>
          Entity(s.identity, s.name, Seq.empty[Stock])
        }
      }
    }
  }

  override def store(entity: Entity): EitherAppliedFuture[Entity] = {
    val q = (shops returning shops.map { _.identity }) += Shop(entity.identity,
                                                               entity.name)
    val a = q.asTry
      .map {
        case Success(id) => Right(entity.copy(identity = Some(id)))
        case Failure(e) => Left(e)
      }

    db.run(a)
  }

  override def stock(shopId: ShopId,
                     stocks: Seq[Stock]): EitherAppliedFuture[Entity] = {
    ???
  }
}

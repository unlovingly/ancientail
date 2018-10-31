package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.shop.{
  ShopId,
  ShopRepository,
  Stock,
  Shop => Entity
}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

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
          Entity(s.identity, s.name, Seq.empty)
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
          Entity(s.identity, s.name, Seq.empty)
        }
      }
    }
  }

  override def store(entity: Entity): EitherAppliedFuture[Entity] = {
    val inserter = (id: ShopId, s: Stock) => {
      (stocks returning stocks).insertOrUpdate(
        Stock(shopId = id,
              productId = s.productId,
              amount = s.amount,
              price = s.price))
    }

    val q = for {
      shopId <- (shops returning shops.map { _.identity })
        .insertOrUpdate(Shop(entity.identity, entity.name))
      ss <- shopId match {
        case Some(id) =>
          DBIO.sequence(entity.stocks.map(inserter(id, _)))
        case None =>
          DBIO.sequence(entity.stocks.map(inserter(entity.identity.get, _)))
      }
    } yield (shopId, ss)

    val query = (shops returning shops.map { _.identity })
      .insertOrUpdate(Shop(entity.identity, entity.name))
      .flatMap {
        case Some(id) =>
          DBIO.sequence(entity.stocks.map(inserter(id, _)))
        case None =>
          DBIO.sequence(entity.stocks.map(inserter(entity.identity.get, _)))
      }
      .transactionally

    // try[seq[option[(shopid, stock)]]]
    val actions = q.asTry
      .map { _.toEither }
      .map {
        _.map {
          case (optionalShopId, sequencialOptionalStocks: Seq[Option[Stock]]) =>
            val id = optionalShopId.get
            val unveiled = sequencialOptionalStocks.map {
              case Some(sss) =>
                sss
            }

            entity.copy(identity = Some(id), stocks = unveiled)
        }
      }

    db.run(actions)
  }

  override def retrieveWithStock(
    shopId: ShopId,
    productIds: Seq[ProductId]): EitherAppliedFuture[Seq[Entity]] = {

    val q1 = shops.filter(_.identity === shopId).result
    val q2 = stocks.filter(_.shopId === shopId).result

    val a = (q1 zip q2).asTry.map { _.toEither }

    // 本当は Either[_, Entity] したい
    db.run(a).map { e =>
      e.map {
        case (h, t) =>
          h.map { o =>
            Entity(o.identity, o.name, t)
          }
      }
    }
  }
}

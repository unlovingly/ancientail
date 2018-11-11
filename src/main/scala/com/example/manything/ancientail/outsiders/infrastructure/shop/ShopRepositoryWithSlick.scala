package com.example.manything.ancientail.outsiders.infrastructure.shop

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.shop.{
  PluCode,
  ShopId,
  ShopRepository,
  Stock,
  Shop => Entity
}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class ShopRepositoryWithSlick(implicit val db: Database,
                              implicit val executionContext: ExecutionContext)
  extends ShopRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits._

    val q = shops.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map { _.map { _.to() } }
  }

  override def retrieve(id: Seq[Identifier]): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits._

    val q = for {
      p <- shops if p.identity.inSet(id)
    } yield p
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a)).map { _.map { _.to() } }
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits._

    val q1 = (shops returning shops.map { _.identity }) +=
      Shop(identity = entity.identity, name = entity.name)
    val q2 = q1.flatMap { id =>
      store(id, entity.stocks)

      q1
    }

    val a = q2.asTry.map { _.toEither }

    EitherT(db.run(a)).map { id =>
      entity.copy(identity = Some(id))
    }
  }

  private def store(id: ShopId, ss: Seq[Stock]) = {
    val targets = ss.map { s =>
      Stock(pluCode = PluCode
              .generate(v = s.productId, a = s.price),
            shopId = id,
            productId = s.productId,
            amount = s.amount,
            price = s.price)
    }

    DBIO.sequence {
      targets.map { t =>
        (stocks returning stocks).insertOrUpdate(t)
      }
    }
  }

  override def retrieveWithStocks(
    shopId: Identifier,
    productIds: Seq[ProductId]): EitherTFuture[EntityType] = {
    import cats.implicits._

    val q1 = shops.filter(_.identity === shopId).result
    val q2 = stocks.filter(_.shopId === shopId).result

    val a = (q1 zip q2).asTry.map { _.toEither }

    EitherT(db.run(a)).map {
      case (h, t) =>
        val p = h.head

        Entity(p.identity, p.name, t)
    }
  }

  /**
   * 棚卸し処理、理論在庫状態を保存する
   * TODO
   * @return
   */
  override def inventory(copyTo: String): EitherTFuture[Unit] = {
    val startedAt = "2018/01/01"
    val finishedAt = "2018/03/31"
    val creater =
      sqlu"create table $copyTo partition of snapshots for values from ('$startedAt') to ('$finishedAt')"
    val inserter =
      sqlu"insert into $copyTo (c1, c2) select (c1, c2) from stocks where amount > 0"

    val q = DBIO.seq(creater, inserter).transactionally.asTry.map { _.toEither }

    EitherT(db.run(q))
  }
}

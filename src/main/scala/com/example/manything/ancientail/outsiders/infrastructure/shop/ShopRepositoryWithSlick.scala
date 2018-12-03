package com.example.manything.ancientail.outsiders.infrastructure.shop

import scala.concurrent.ExecutionContext

import cats.data.EitherT

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.shop._
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._
import com.example.manything.outsiders.slick.NotFoundException

class ShopRepositoryWithSlick(val db: Database)(
  implicit val executionContext: ExecutionContext)
  extends ShopRepository[EitherTFuture] {
  override def retrieve(): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = shops.take(20)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.nonEmpty)
      .map { _.map { _.to() } }
  }

  override def retrieve(id: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val q = for {
      p <- shops if p.identity === id.bind
    } yield p
    val a = q.result.head.asTry.map { _.toEither }

    EitherT(db.run(a)).map { _.to() }
  }

  override def store(entity: EntityType): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val query = for {
      shopId <- (shops returning shops.map { _.identity })
        .insertOrUpdate(PolishedShop.from(entity))
      _ <- store(shopId, entity.stocks)
    } yield shopId

    val a = query.asTry.map { _.toEither }

    EitherT(db.run(a)).map { id =>
      entity.copy(identity = id)
    }
  }

  private def store(id: Option[ShopId], ss: Seq[Stock]) = {
    val targets = ss.map { s =>
      Stock(pluCode = PluCode
              .generate(v = s.productId, a = s.price),
            shopId = id.get,
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

  override def retrieveWithStocksBy(
    shopId: Identifier): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    // TODO productId
    val s = stocks filter (_.amount > 0)
    val q = shops joinLeft s on (_.identity === _.shopId)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.nonEmpty)
      .map {
        _.groupBy(_._1).map {
          case (shop, stock) =>
            val ss = stock.flatMap(_._2)

            shop.to(ss)
        }.head
      }
  }

  override def retrieveWithStocksBy(
    shopId: Identifier,
    code: PluCode): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    val s = stocks filter (_.pluCode === code.bind) filter (_.amount > 0)
    val q = shops joinLeft s on (_.identity === _.shopId) filter (_._1.identity === shopId.bind)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.nonEmpty)
      .map {
        _.groupBy(_._1).map {
          case (shop, stock) =>
            val ss = stock.flatMap(_._2)

            shop.to(ss)
        }.head
      }
  }

  override def retrieveWithStocksBy(
    q: String): EitherTFuture[Seq[EntityType]] = {
    import cats.implicits.catsStdInstancesForFuture

    import com.example.manything.ambientendre.outsiders.infrastructure.product._

    val query = for {
      p <- products if p.name.toLowerCase like s"%$q%".toLowerCase.bind
      t <- stocks if p.identity === t.productId && t.amount > 0
      h <- shops if t.shopId === h.identity
    } yield (h, t)

    val a = query.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.nonEmpty)
      .map {
        _.groupBy(_._1).map {
          case (shop, stock) =>
            shop.to(stock.map(_._2))
        }.toSeq
      }
  }

  override def retrieveWithStocksBy(
    shopId: Identifier,
    productIds: Seq[ProductId]): EitherTFuture[EntityType] = {
    import cats.implicits.catsStdInstancesForFuture

    // TODO productId
    val t = stocks filter (_.amount > 0)
    val h = shops filter (_.identity === shopId.bind)
    val q = h joinLeft t on (_.identity === _.shopId)
    val a = q.result.asTry.map { _.toEither }

    EitherT(db.run(a))
      .ensure(NotFoundException())(_.nonEmpty)
      .map {
        _.groupBy(_._1).map {
          case (shop, stock) =>
            val ss = stock.flatMap(_._2)

            shop.to(ss)
        }.head
      }
  }

  /**
   * 棚卸し処理、理論在庫状態を保存する
   * TODO
   *
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

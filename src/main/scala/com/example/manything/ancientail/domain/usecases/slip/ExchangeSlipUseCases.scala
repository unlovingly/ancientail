package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import cats.MonadError

import com.example.manything.ancientail.domain.models.shop.{
  PluCode,
  ShopRepository
}
import com.example.manything.ancientail.domain.models.slip.exchange.{
  ExchangeSlip,
  ExchangeSlipRepository
}

class ExchangeSlipUseCases[A[_]](val shops: ShopRepository[A],
                                 val slips: ExchangeSlipRepository[A])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[A]
  with ExchangeProducts[A] {
  override type EntityType = ExchangeSlip

  override def exchange(slip: ExchangeSlip)(
    implicit ME: MonadError[A, Throwable]): A[ExchangeSlip] = {
    import cats.implicits.{toFlatMapOps, toFunctorOps}

    val productIds = slip.items.map(i => PluCode.generate(i.productId, i.price))
    val receiver = shops.retrieveWithStocksBy(slip.receiverId, productIds)
    val sender = shops.retrieveWithStocksBy(slip.senderId, productIds)

    val result = for {
      r <- receiver.map(_.inbound(slip))
      s <- sender.map(_.outbound(slip))
      _ <- shops.store(r)
      _ <- shops.store(s)
      result <- slips.store(slip)
    } yield result

    result
  }
}

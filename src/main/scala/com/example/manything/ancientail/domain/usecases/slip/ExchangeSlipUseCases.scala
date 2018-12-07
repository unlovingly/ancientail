package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import cats.Functor

import com.example.manything.ancientail.domain.models.shop.ShopRepository
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
    implicit F: Functor[A]): A[ExchangeSlip] = {
    import cats.syntax.functor.toFunctorOps

    val productIds = slip.items.map(_.productId)
    val receiver = shops.retrieveWithStocksBy(slip.receiverId, productIds)
    val sender = shops.retrieveWithStocksBy(slip.senderId, productIds)
    // 1. 伝票を保存して
    val result = slips.store(slip)

    receiver.map { s =>
      val h = s.inbound(slip)

      shops.store(h)
    }

    sender.map { s =>
      val h = s.outbound(slip)

      shops.store(h)
    }

    result
  }
}

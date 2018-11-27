package com.example.manything.ancientail.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.exchange.{
  ExchangeSlip,
  ExchangeSlipRepository
}

class ExchangeSlipUseCases(val shops: ShopRepository[EitherTFuture],
                           val slips: ExchangeSlipRepository[EitherTFuture])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[EitherTFuture]
  with ExchangeProducts[EitherTFuture] {
  override type EntityType = ExchangeSlip

  override def exchange(slip: ExchangeSlip): EitherTFuture[ExchangeSlip] = {
    import cats.implicits.catsStdInstancesForFuture

    val productIds = slip.items.map(_.productId)
    val receiver = shops.retrieveWithStocks(slip.receiverId, productIds)
    val sender = shops.retrieveWithStocks(slip.senderId, productIds)
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

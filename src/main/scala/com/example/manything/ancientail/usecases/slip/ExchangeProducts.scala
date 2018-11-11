package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlip

/**
 * 入庫ユースケース
 */
trait ExchangeProducts { this: SlipUseCases =>

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def exchange(slip: ExchangeSlip): EitherTFuture[ExchangeSlip] = {
    import cats.implicits._

    val productIds = slip.items.map(_.productId)
    val receiver = shops.retrieveWithStocks(slip.receiverId, productIds)
    val sender = shops.retrieveWithStocks(slip.senderId, productIds)
    // 1. 伝票を保存して
    val result = exchangeSlips.store(slip)

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

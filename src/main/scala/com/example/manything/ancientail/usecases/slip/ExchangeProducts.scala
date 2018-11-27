package com.example.manything.ancientail.usecases.slip

import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlip

/**
 * 入庫ユースケース
 */
trait ExchangeProducts[A[_]] {

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def exchange(slip: ExchangeSlip): A[ExchangeSlip]
}

package com.example.manything.ancientail.domain.usecases.slip

import cats.Functor

import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlip

/**
 * 入庫ユースケース
 */
trait ExchangeProducts[A[_]] {

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def exchange(slip: ExchangeSlip)(implicit F: Functor[A]): A[ExchangeSlip]
}

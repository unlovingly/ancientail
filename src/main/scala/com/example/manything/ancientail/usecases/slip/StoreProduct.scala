package com.example.manything.ancientail.usecases.slip
import com.example.manything.ancientail.domain.slip.Slip

import scala.concurrent.Future

trait StoreProduct { this: SlipUseCases =>

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   * @param slip
   */
  def store(slip: Slip): Future[Slip]
}

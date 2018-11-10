package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

/**
 * 伝票
 */
trait SlipBase {
  type SenderIdType

  val identity: Option[SlipId] = None

  /**
   * 発行者
   */
  val senderId: SenderIdType

  /**
   * 受領者
   */
  val receiverId: ShopId
}

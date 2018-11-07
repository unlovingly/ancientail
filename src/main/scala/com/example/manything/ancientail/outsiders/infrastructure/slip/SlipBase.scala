package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

trait SlipBase {
  type SenderId

  val identity: Option[SlipId] = None
  val senderId: SenderId
  val receiverId: ShopId
}

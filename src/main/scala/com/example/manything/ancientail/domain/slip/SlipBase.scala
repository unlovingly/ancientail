package com.example.manything.ancientail.domain.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.Entity

trait SlipBase extends Entity {
  override type Identifier = SlipId
  type SenderId

  override val identity: Option[SlipId] = None
  val senderId: SenderId
  val receiverId: ShopId
  val items: Seq[SlipItem]
}

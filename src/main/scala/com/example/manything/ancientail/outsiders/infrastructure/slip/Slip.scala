package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.roundelayout.domain.Identifiability

trait Slip[A <: Identifiability[_, _]] {
  val identity: Option[SlipId] = None
  val senderId: A
  val receiverId: ShopId
}

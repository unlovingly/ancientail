package com.example.manything.ancientail.domain.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

abstract class Slip[A <: Identifiability[_, _]](
  val identity: Option[SlipId] = None,
  val senderId: A,
  val receiverId: ShopId,
  val items: Seq[SlipItem]
) extends Entity[SlipId] {
  def copy(identity: Option[SlipId] = identity,
           senderId: A = senderId,
           receiverId: ShopId = receiverId,
           items: Seq[SlipItem] = items): Slip[A]
}

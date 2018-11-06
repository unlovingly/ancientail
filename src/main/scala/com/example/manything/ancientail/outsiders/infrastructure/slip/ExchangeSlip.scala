package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  ExchangeSlip => Entity,
  Slip => EntityBase,
  SlipItem => EntityItem
}

case class ExchangeSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId
) extends Slip[ShopId] {
  def as(items: Seq[EntityItem] = Seq.empty): EntityBase[ShopId] = {
    Entity(identity = identity,
           senderId = senderId,
           receiverId = receiverId,
           items = items)
  }
}

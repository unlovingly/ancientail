package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  Slip => EntityBase,
  PurchaseSlip => Entity,
  SlipItem => EntityItem
}
case class PurchaseSlip(
  override val identity: Option[SlipId[PublisherId]] = None,
  override val senderId: PublisherId,
  override val receiverId: ShopId
) extends Slip[PublisherId] {
  def as(items: Seq[EntityItem] = Seq.empty): EntityBase[PublisherId] = {
    Entity(identity = identity,
           senderId = senderId,
           receiverId = receiverId,
           items = items)
  }
}

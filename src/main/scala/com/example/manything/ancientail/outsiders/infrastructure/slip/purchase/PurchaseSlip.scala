package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase
import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.ancientail.outsiders.infrastructure.slip.Slip

case class PurchaseSlip(
  override val identity: Option[SlipId] = None,
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

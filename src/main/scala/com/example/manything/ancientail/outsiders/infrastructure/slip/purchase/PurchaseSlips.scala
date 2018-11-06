package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class PurchaseSlips(tag: Tag)
  extends Table[PurchaseSlip](tag, "purchase_slips") {
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher._
  import com.example.manything.ancientail.outsiders.infrastructure.shop._

  def identity: Rep[SlipId] = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def senderId: Rep[PublisherId] = column[PublisherId]("sender_id")
  def receiverId: Rep[ShopId] = column[ShopId]("receiver_id")

  def * =
    (identity.?, senderId, receiverId) <> (PurchaseSlip.tupled, PurchaseSlip.unapply)
}

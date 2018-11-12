package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import slick.lifted.Tag

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipsBase
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class PurchaseSlips(tag: Tag)
  extends SlipsBase[PurchaseSlip](tag, "purchase_slips") {
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher._
  import com.example.manything.ancientail.outsiders.infrastructure.shop._
  import com.example.manything.ancientail.outsiders.infrastructure.slip._

  def senderId = column[PublisherId]("sender_id")

  def * =
    (identity.?, senderId, receiverId, publishedAt) <> (PurchaseSlip.tupled, PurchaseSlip.unapply)
}

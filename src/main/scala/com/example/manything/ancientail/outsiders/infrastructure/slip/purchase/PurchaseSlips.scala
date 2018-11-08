package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipsBase
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class PurchaseSlips(tag: Tag)
  extends SlipsBase[PurchaseSlip](tag, "purchase_slips") {
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher._
  import com.example.manything.ancientail.outsiders.infrastructure.shop._
  import com.example.manything.ancientail.outsiders.infrastructure.slip._

  def senderId = column[PublisherId]("publisher_id")

  def * =
    (identity.?, senderId, receiverId) <> (PurchaseSlip.tupled, PurchaseSlip.unapply)
}

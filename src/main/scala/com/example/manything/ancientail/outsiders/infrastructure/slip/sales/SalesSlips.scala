package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import slick.lifted.Tag

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipsBase
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class SalesSlips(tag: Tag) extends SlipsBase[SalesSlip](tag, "purchase_slips") {
  import com.example.manything.ancientail.outsiders.infrastructure.shop._
  import com.example.manything.ancientail.outsiders.infrastructure.slip._

  def senderId = column[ShopId]("shop_id")

  def * =
    (identity.?, senderId, receiverId, publishedAt) <> (SalesSlip.tupled, SalesSlip.unapply)
}

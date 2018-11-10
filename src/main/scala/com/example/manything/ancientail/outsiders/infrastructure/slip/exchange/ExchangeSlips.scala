package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipsBase
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._
import slick.lifted.Tag

class ExchangeSlips(tag: Tag)
  extends SlipsBase[ExchangeSlip](tag, "exchange_slips") {
  import com.example.manything.ancientail.outsiders.infrastructure.shop._
  import com.example.manything.ancientail.outsiders.infrastructure.slip._

  def senderId = column[ShopId]("shop_id")

  def * =
    (identity.?, senderId, receiverId) <> (ExchangeSlip.tupled, ExchangeSlip.unapply)
}

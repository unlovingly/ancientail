package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class ExchangeSlips(tag: Tag)
  extends Table[ExchangeSlip](tag, "exchange_slips") {
  import com.example.manything.ancientail.outsiders.infrastructure.shop._
  import com.example.manything.ancientail.outsiders.infrastructure.slip._

  def identity = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def senderId = column[ShopId]("shop_id")
  def receiverId = column[ShopId]("shop_id")

  def * =
    (identity.?, senderId, receiverId) <> (ExchangeSlip.tupled, ExchangeSlip.unapply)
}

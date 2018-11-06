package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.roundelayout.domain.Identifiability
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

abstract class Slips[A <: Identifiability[_, _]](tag: Tag, _tableName: String)
  extends Table[Slip[A]](tag, _tableName) {
  import com.example.manything.ancientail.outsiders.infrastructure.shop._

  def identity: Rep[SlipId] = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def senderId: Rep[A] = column[A]("sender_id")
  def receiverId: Rep[ShopId] = column[ShopId]("receiver_id")

  def * =
    (identity.?, senderId, receiverId) <> (Slip[A].tupled, Slip[A].unapply)
}

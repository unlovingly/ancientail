package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class Slips(tag: Tag) extends Table[Slip](tag, "slips") {
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher._
  import com.example.manything.ancientail.outsiders.infrastructure.shop._

  def identity = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def senderId = column[PublisherId]("publisher_id")
  def receiverId = column[ShopId]("shop_id")

  def * =
    (identity.?, senderId, receiverId) <> (Slip.tupled, Slip.unapply)
}

object Slips {
  implicit class SlipsExtensions[C[_]](q: Query[Slips, Slip, C]) {
    def withItems =
      q.join(slipItems)
        .on(_.identity === _.slipId)
  }
}

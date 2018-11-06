package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  Slip => Entity,
  SlipItem => EntityItem
}
import com.example.manything.roundelayout.domain.Identifiability

abstract class Slip[A <: Identifiability[_, _]] {
  val identity: Option[SlipId] = None
  val senderId: A
  val receiverId: ShopId
  def as(items: Seq[EntityItem] = Seq.empty): Entity[_]
}

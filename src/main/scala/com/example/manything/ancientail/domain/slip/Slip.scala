package com.example.manything.ancientail.domain.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.Entity

case class Slip(
  identity: Option[SlipId] = None,
  senderId: PublisherId,
  receiverId: ShopId,
  items: Seq[SlipItemId] // 怪しい
) extends Entity[SlipId] {}

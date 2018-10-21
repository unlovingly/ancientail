package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

case class Slip(
  identity: Option[SlipId] = None,
  senderId: PublisherId,
  receiverId: ShopId
)

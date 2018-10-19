package com.example.manything.ancientail.domain.shop

import com.example.manything.roundelayout.domain.Entity

case class Shop(
  identity: Option[ShopId] = None,
  name: String
) extends Entity[ShopId] {}

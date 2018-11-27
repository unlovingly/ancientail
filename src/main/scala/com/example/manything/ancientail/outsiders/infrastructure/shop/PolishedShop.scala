package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ancientail.domain.shop.{Shop, ShopId}

// https://stackoverflow.com/a/42218139/1764794
case class PolishedShop(identity: Option[ShopId] = None, name: String) {
  def to(): Shop =
    Shop(identity = identity, name = name, stocks = Seq.empty)
}

object PolishedShop {
  def from(e: Shop): PolishedShop =
    PolishedShop(identity = e.identity, name = e.name)
}

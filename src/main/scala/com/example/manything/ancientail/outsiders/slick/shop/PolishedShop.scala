package com.example.manything.ancientail.outsiders.slick.shop

import com.example.manything.ancientail.domain.models.shop._

// https://stackoverflow.com/a/42218139/1764794
case class PolishedShop(identity: Option[ShopId] = None, name: String) {
  def to(stocks: Seq[Stock] = Seq.empty): Shop =
    Shop(identity = identity, name = name, stocks = stocks)
}

object PolishedShop {
  def from(e: Shop): PolishedShop =
    PolishedShop(identity = e.identity, name = e.name)
}

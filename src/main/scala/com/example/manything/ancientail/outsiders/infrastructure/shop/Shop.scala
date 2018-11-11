package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ancientail.domain.shop.{ShopId, Shop => Entity}

// https://stackoverflow.com/a/42218139/1764794
case class Shop(identity: Option[ShopId] = None, name: String) {
  def to(): Entity =
    Entity(identity = identity, name = name, stocks = Seq.empty)
}

object Shop {
  def from(e: Entity): Shop =
    Shop(identity = e.identity, name = e.name)
}

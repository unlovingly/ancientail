package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ancientail.domain.shop.ShopId

// https://stackoverflow.com/a/42218139/1764794
case class Shop(identity: Option[ShopId] = None, name: String)

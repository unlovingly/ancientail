package com.example.manything.ambientendre.domain.product

import com.example.manything.roundelayout.domain.Entity

case class Product(
  identity: Option[ProductId] = None,
  name: String
) extends Entity[ProductId] { // FIXME
}

package com.example.manything.ambientendre.domain

import com.example.manything.ambientendre.domain.Product.{Price, ProductID}
import com.example.manything.roundelayout.{Entity, Identifiability, Nonidentifier}

/**
 *
 * @param identity
 * @param code
 * @param publisher
 * @param buying
 * @param selling
 * @param name
 */
case class Product(
  identity: Identifiability[ProductID] = Nonidentifier[ProductID](),
  code: PLUCode,
  publisher: Publisher,
  buying: Price,
  selling: Price,
  name: String,
) extends Entity[ProductID] {
}

object Product {
  type ProductID = Long
}

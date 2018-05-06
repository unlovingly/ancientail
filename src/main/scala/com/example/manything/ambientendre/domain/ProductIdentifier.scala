package com.example.manything.ambientendre.domain

import com.example.manything.roundelayout.Identifier

case class ProductIdentifier(
  override val id: ProductID
) extends Identifier[ProductID](id: ProductID) {

}

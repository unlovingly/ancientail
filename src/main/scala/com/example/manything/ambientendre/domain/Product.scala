package com.example.manything.ambientendre.domain

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.Publisher
import com.example.manything.roundelayout.domain._

case class Product(
  identity: Option[Identifiability[UUID, Product]] = None,
  name: String
) extends Entity[Identifiability[UUID, Product]] { // FIXME
}

package com.example.manything.ancientail.domain.product

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.roundelayout.domain.Entity

case class Product(
  identity: Option[ProductId] = None,
  name: String,
  publisherId: PublisherId
) extends Entity[ProductId] {}

package com.example.manything.ambientendre.domain.product

import com.example.manything.ambientendre.domain.publisher.Publisher
import com.example.manything.roundelayout.domain.Entity

case class Product(
  identity: Option[ProductId] = None,
  name: String,
  publisher: Publisher
) extends Entity[ProductId] {}

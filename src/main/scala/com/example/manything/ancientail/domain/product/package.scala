package com.example.manything.ancientail.domain

import java.util.UUID

import com.example.manything.roundelayout.domain.Identifiability

package object product {
  type ProductId = Identifiability[UUID, product.Product]
}

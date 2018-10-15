package com.example.manything.ambientendre.domain

import java.util.UUID
import com.example.manything.roundelayout.domain.Identifiability

package object product {
  type ProductId = Identifiability[UUID, Product]
}

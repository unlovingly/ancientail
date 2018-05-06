package com.example.manything.ambientendre.domain

// おそらく Tagged になる
case class Price(amount: Long) {
  assert(amount >= 0)
}

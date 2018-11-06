package com.example.manything.ancientail.domain

import java.util.UUID

import com.example.manything.roundelayout.domain.Identifiability

package object slip {
  type SlipId = Identifiability[UUID, Slip[_]]
  type SlipItemId = Identifiability[UUID, SlipItem]
  type Amount = Int
  type Price = Int
}

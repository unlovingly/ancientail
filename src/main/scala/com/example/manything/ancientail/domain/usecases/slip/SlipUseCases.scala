package com.example.manything.ancientail.domain.usecases.slip

import com.example.manything.ancientail.domain.models.slip.{ Slip, SlipRepository }

trait SlipUseCases[A[_]] extends ShowSlips[A] {
  type EntityType <: Slip

  val slips: SlipRepository[EntityType, A]
}

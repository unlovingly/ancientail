package com.example.manything.ancientail.usecases.slip

import com.example.manything.ancientail.domain.slip.{Slip, SlipRepository}

trait SlipUseCases[A[_]] extends RetrieveSlips[A] with RetrieveOneSlip[A] {
  type EntityType <: Slip

  val slips: SlipRepository[EntityType, A]
}

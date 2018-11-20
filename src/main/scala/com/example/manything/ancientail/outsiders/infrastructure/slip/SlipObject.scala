package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.slip.{SlipBase => Entity}

trait SlipObject[+A <: SlipBase] {
  implicit def self: SlipObject[A] = this
  def from(e: Entity): A
}

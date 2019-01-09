package com.example.manything.roundelayout.domain

trait Entity {
  type Identifier <: Identifiability[_, _]

  val identity: Option[Identifier]
}

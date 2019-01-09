package com.example.manything.roundelayout.domain

trait Repository[A <: Entity] {
  type EntityType <: Entity
  type Identifier <: Identifiability[A, _]
}

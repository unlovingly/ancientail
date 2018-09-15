package com.example.manything.ambientendre.domain

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ProductRepository extends Repository[Product, UUID] {
}

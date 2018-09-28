package com.example.manything.ancientail.domain.product

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ProductRepository[C[_]] extends Repository[Product, UUID, C] {}

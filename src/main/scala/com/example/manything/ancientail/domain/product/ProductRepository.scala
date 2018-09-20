package com.example.manything.ancientail.domain.product

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ProductRepository extends Repository[Product, UUID] {}

package com.example.manything.ambientendre.domain.product

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ProductRepository extends Repository[Product, UUID] {}

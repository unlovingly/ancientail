package com.example.manything.ancientail.domain.shop

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait ShopRepository[A[_]] extends Repository[Shop, UUID, A] {}

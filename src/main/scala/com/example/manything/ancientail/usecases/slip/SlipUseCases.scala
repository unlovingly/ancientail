package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.SlipRepository

import scala.concurrent.ExecutionContext

class SlipUseCases(implicit val slips: SlipRepository[EitherAppliedFuture],
                   implicit val shops: ShopRepository[EitherAppliedFuture],
                   implicit val executionContext: ExecutionContext)
  extends RetrieveSlips
  with RetrieveOneSlip
  with StoringProducts

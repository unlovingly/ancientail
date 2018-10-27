package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.SlipRepository

class SlipUseCases(implicit val slips: SlipRepository[EitherAppliedFuture],
                   implicit val shops: ShopRepository[EitherAppliedFuture])
  extends RetrieveProducts
  with StoringProducts

package com.example.manything.ancientail.usecases.slip

import scala.concurrent.ExecutionContext

class SlipUseCases(implicit val executionContext: ExecutionContext)
  extends RetrieveProducts
  with ExchangingProducts
  with StoringProducts

package com.example.manything.ancientail.usecases.slip

import com.example.manything.ancientail.domain.slip.SlipRepository

import scala.concurrent.Future

class SlipUseCases(implicit val slips: SlipRepository[Future])

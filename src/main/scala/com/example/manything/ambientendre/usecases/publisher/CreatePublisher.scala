package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherRepository
}
import com.example.manything.roundelayout.usecase.UseCase

import scala.concurrent.Future

class CreatePublisher(publishers: PublisherRepository[Future])
  extends UseCase[Unit, Publisher] {
  override def realize(p: Unit): Future[Publisher] =
    ???
}

package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherRepository
}
import com.example.manything.roundelayout.usecase.UseCase

import scala.concurrent.Future

class ListingPublishers[C[_]](publishers: PublisherRepository[Future])
  extends UseCase[Unit, Seq[Publisher]] {
  def realize(p: Unit): Future[Seq[Publisher]] = publishers.retrieve
}

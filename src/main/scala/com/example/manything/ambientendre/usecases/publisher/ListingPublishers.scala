package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherRepository
}
import com.example.manything.roundelayout.usecase.UseCase

class ListingPublishers[C[_]](publishers: PublisherRepository[C])
  extends UseCase[Seq[Publisher], C] {
  def realize(): C[Seq[Publisher]] = publishers.retrieve
}

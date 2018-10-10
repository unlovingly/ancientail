package com.example.manything.ambientendre.usecases.publisher
import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherRepository
}

import scala.concurrent.Future

class PublisherUseCases(implicit val publishers: PublisherRepository[Future])
  extends CreatePublisher
  with ListingPublishers {}

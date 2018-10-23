package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.publisher.PublisherRepository

class PublisherUseCases(
  implicit val publishers: PublisherRepository[EitherAppliedFuture])
  extends CreatePublisher
  with ListingPublishers

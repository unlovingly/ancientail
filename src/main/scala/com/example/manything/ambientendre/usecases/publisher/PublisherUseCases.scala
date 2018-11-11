package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.publisher.PublisherRepository

class PublisherUseCases(
  implicit val publishers: PublisherRepository[EitherTFuture])
  extends CreatePublisher
  with ListingPublishers

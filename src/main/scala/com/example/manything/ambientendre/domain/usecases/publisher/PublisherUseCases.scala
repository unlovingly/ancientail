package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.publisher.PublisherRepository

class PublisherUseCases(val publishers: PublisherRepository[EitherTFuture])
  extends CreatePublisher
  with ListingPublishers

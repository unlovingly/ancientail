package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.ambientendre.domain.models.publisher.PublisherRepository

class PublisherUseCases[A[_]](val publishers: PublisherRepository[A])
  extends CreatePublisher[A]
  with ShowPublishers[A]

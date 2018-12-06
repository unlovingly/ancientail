package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.publisher.Publisher

trait CreatePublisher { this: PublisherUseCases =>
  def create(p: Publisher): EitherTFuture[Publisher] =
    publishers.store(p)
}

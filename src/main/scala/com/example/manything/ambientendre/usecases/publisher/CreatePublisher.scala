package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.publisher.Publisher

trait CreatePublisher {
  this: PublisherUseCases =>
  def create(p: Publisher): EitherAppliedFuture[Publisher] =
    publishers.store(p)
}

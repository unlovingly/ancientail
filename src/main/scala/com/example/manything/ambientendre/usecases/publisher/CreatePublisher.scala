package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.publisher.Publisher

trait CreatePublisher {
  this: PublisherUseCases =>
  def create(p: Publisher): EitherTFuture[Publisher] =
    publishers.store(p)
}

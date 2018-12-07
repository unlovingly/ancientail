package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.ambientendre.domain.models.publisher.Publisher

trait CreatePublisher[A[_]] { this: PublisherUseCases[A] =>
  def create(p: Publisher): A[Publisher] =
    publishers.store(p)
}

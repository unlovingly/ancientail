package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.ambientendre.domain.publisher.Publisher

import scala.concurrent.Future

trait CreatePublisher {
  this: PublisherUseCases =>
  def create(p: Publisher): Future[Publisher] =
    publishers.store(p)
}

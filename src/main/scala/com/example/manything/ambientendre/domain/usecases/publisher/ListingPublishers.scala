package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.publisher._

trait ListingPublishers {
  this: PublisherUseCases =>
  def list(): EitherTFuture[Seq[Publisher]] = publishers.retrieve()
}

package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.publisher._

trait ListingPublishers {
  this: PublisherUseCases =>
  def list(): EitherTFuture[Seq[Publisher]] = publishers.retrieve()
}

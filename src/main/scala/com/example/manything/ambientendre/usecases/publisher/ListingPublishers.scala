package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.publisher._

trait ListingPublishers {
  this: PublisherUseCases =>
  def list(): EitherAppliedFuture[Seq[Publisher]] = publishers.retrieve()
}

package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.ambientendre.domain.publisher._

import scala.concurrent.Future

trait ListingPublishers {
  this: PublisherUseCases =>
  def list(id: Seq[PublisherId]): Future[Seq[Publisher]] = publishers.retrieve
}

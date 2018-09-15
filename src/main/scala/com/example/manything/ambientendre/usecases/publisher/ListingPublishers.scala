package com.example.manything.ambientendre.usecases.publisher

import com.example.manything.ambientendre.domain.publisher.{Publisher, PublisherRepository}
import com.example.manything.roundelayout.usecase.UseCase

import scala.concurrent.Future

class ListingPublishers(publishers: PublisherRepository) extends UseCase[Publisher] {
  def list: Future[Seq[Publisher]] = publishers.retrieve
}

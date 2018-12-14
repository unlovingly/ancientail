package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.ambientendre.domain.models.publisher._

trait ShowPublishers[A[_]] {
  this: PublisherUseCases[A] =>
  def retrieve(): A[Seq[Publisher]] = publishers.retrieve()
  def retrieve(publisherId: Seq[PublisherId]): A[Seq[Publisher]] =
    publishers.retrieve(publisherId)
}

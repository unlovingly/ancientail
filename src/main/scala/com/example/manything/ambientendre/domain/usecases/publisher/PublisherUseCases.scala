package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.ambientendre.domain.models.publisher._

class PublisherUseCases[A[_]](val publishers: PublisherRepository[A])
  extends CreatePublisher[A]
  with ShowPublishers[A] {
  override def create(p: Publisher): A[Publisher] =
    publishers.store(p)
  override def retrieve(): A[Seq[Publisher]] = publishers.retrieve()
  override def retrieve(publisherId: Seq[PublisherId]): A[Seq[Publisher]] =
    publishers.retrieve(publisherId)
}

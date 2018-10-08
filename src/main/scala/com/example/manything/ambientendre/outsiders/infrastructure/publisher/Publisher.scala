package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import com.example.manything.ambientendre.domain.publisher.PublisherId

// TODO Iso[Entity, Publisher]
case class Publisher(
  identity: Option[PublisherId] = None,
  name: String
)

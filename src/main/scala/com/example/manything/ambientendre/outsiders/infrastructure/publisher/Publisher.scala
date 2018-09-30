package com.example.manything.ambientendre.outsiders.infrastructure.publisher

import com.example.manything.ambientendre.domain.publisher.PublisherId

case class Publisher(
  identity: Option[PublisherId] = None,
  name: String
)

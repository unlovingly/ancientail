package com.example.manything.ambientendre.domain.publisher

import com.example.manything.roundelayout.domain._

case class Publisher(
    identity: Option[PublisherId] = None,
    name: String
) extends Entity[PublisherId] { // FIXME
}

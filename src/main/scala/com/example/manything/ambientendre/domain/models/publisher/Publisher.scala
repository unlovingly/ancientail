package com.example.manything.ambientendre.domain.models.publisher

import java.util.UUID

import com.example.manything.roundelayout.domain.{ Entity, Identifiability }

/**
 * 販売者
 * ライセンシーであることが多く、店舗に製品を卸している
 *
 * @param identity Option[PublisherId]
 * @param name 販売者名
 */
case class Publisher(
    override val identity: Option[PublisherId] = None,
    name: String
) extends Entity {
  override type Identifier = PublisherId
}

case class PublisherId(override val value: UUID) extends Identifiability[Publisher, UUID]

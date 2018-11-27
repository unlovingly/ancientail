package com.example.manything.ancientail.domain.slip

import java.time.ZonedDateTime
import java.util.UUID

import com.example.manything.roundelayout.domain.{Entity, Identifiability}

/**
 * 伝票
 *
 * @note 入出庫履歴を別途用意すべきかもしれない
 */
trait Slip extends Entity {
  override type Identifier = SlipId

  type ReceiverIdType <: Identifiability[_, UUID]
  type SenderIdType <: Identifiability[_, UUID]

  /**
   * 伝票番号
   * Publisher によっては `00001` など固定桁になっていることがあるので文字列
   */
  val number: String

  /**
   * 受領者
   */
  val receiverId: ReceiverIdType

  /**
   * 発行者
   */
  val senderId: SenderIdType

  /**
   * 対象 Product
   */
  val items: Seq[SlipItem]

  /**
   * システム登録日 (伝票処理日)
   */
  val approvedAt: ZonedDateTime

  /**
   * 発行日
   */
  val publishedAt: ZonedDateTime
}

case class SlipId(value: UUID) extends Identifiability[Slip, UUID]

package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.ambientendre.domain.models.publisher._

trait ShowPublishers[A[_]] {

  /**
   * 登録されている販売者を取得する
   */
  def retrieve(): A[Seq[Publisher]]

  /**
   * 登録されている販売者から指定された販売者を取得する
   */
  def retrieve(publisherId: Seq[PublisherId]): A[Seq[Publisher]]
}

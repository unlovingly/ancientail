package com.example.manything.ambientendre.domain.usecases.publisher

import com.example.manything.ambientendre.domain.models.publisher.Publisher

trait CreatePublisher[A[_]] {

  /**
   * 販売者をシステムに登録する
   */
  def create(p: Publisher): A[Publisher]
}

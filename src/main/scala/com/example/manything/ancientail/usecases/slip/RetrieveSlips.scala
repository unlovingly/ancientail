package com.example.manything.ancientail.usecases.slip

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveSlips[A[_]] { this: SlipUseCases[A] =>
  def retrieve(): A[Seq[EntityType]] = {
    slips.retrieve()
  }
}

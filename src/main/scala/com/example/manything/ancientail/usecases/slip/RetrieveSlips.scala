package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.{Slip, SlipRepository}

/**
 * 詳細をみるときのユースケース
 */
trait RetrieveSlips { this: SlipUseCases =>
  def retrieve[SlipType <: Slip]()(
    slips: SlipRepository[SlipType, EitherTFuture])
    : EitherTFuture[Seq[SlipType]] = {
    slips.retrieve()
  }
}

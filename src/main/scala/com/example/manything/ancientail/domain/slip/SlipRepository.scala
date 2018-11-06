package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.{Identifiability, Repository}

/**
 *
 * @tparam A SenderIdType
 * @tparam B MonadType
 */
trait SlipRepository[A, B[_]] extends Repository[Slip[A], UUID, B] {}

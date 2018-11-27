package com.example.manything.blessedict.outsiders

import java.util.UUID

import _root_.slick.lifted.TableQuery

import com.example.manything.blessedict.domain.customer.CustomerId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

package object slick {
  lazy val customers = TableQuery[Customers]

  implicit lazy val customerIdColumnType: BaseColumnType[CustomerId] =
    MappedColumnType
      .base[CustomerId, UUID](_.value, CustomerId.apply)
}

package com.example.manything.blessedict.outsiders.slick

import _root_.slick.lifted.Tag

import com.example.manything.blessedict.domain.customer.{Customer, CustomerId}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Customers(tag: Tag) extends Table[Customer](tag, "customers") {
  def identity = column[CustomerId]("slip_item_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * =
    (identity.?, name) <> (Customer.tupled, Customer.unapply)
}

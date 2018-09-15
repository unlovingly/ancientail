package com.example.manything.ambientendre.outsiders.infrastructure

import java.util.UUID

import com.example.manything.ambientendre.domain.{Product, ProductId}
import com.example.manything.roundelayout.domain.Identifiability
import slick.ast.TypedType
import slick.jdbc.PostgresProfile.api._

class Products(tag: Tag) extends Table[Product](tag, "merchandises") {
  implicit val idColumnType: TypedType[ProductId] =
    MappedColumnType.base[ProductId, UUID](_.value, Identifiability[UUID, Product])

  def identity = column[Identifiability[UUID, Product]]("id", O.PrimaryKey)
  def name = column[String]("name")
  // def publisher = column[Publisher]("publisher")
  def * = (identity.?, name) <> (Product.tupled, Product.unapply)
}

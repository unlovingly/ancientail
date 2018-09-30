package com.example.manything.ancientail.outsiders.infrastructure.product

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.ancientail.domain.product.{
  Product => DProduct,
  ProductId
}
import com.example.manything.roundelayout.domain.Entity

/**
 * domain.Product の DXO
 *
 * @note 本当は domain.Product を Table に関連付けたいが、
 *       join するときは publisher フィールドを trait であてがうとしても
 *       infrastructure.product.Product を定義することになるのであまり変わらない
 * @param identity
 * @param name
 * @param publisherId
 * @see domain.Product
 */
case class Product(
  identity: Option[ProductId] = None,
  name: String,
  publisherId: PublisherId
)

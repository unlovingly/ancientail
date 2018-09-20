package com.example.manything.ancientail.outsiders.infrastructure

import slick.lifted

package object product {
  val products = lifted.TableQuery[Products]
}

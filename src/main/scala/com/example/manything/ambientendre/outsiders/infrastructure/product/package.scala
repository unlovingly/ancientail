package com.example.manything.ambientendre.outsiders.infrastructure

import slick.lifted

package object product {
  val products = lifted.TableQuery[Products]
}

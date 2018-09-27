package com.example.manything.ancientail.outsiders.infrastructure

import slick.lifted

package object product {
  lazy val products = lifted.TableQuery[Products]
}

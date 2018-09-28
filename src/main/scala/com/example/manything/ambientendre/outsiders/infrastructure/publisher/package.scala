package com.example.manything.ambientendre.outsiders.infrastructure

import slick.lifted

package object publisher {
  lazy val publishers = lifted.TableQuery[Publishers]
}

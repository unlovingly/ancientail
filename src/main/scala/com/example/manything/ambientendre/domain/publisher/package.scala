package com.example.manything.ambientendre.domain

import java.util.UUID

import com.example.manything.roundelayout.domain.Identifiability

package object publisher {
  type PublisherId = Identifiability[UUID, Publisher]
}

package com.example

import scala.concurrent.Future

import cats.data.EitherT

package object manything {
  type EitherTFuture[A] = EitherT[Future, Throwable, A]
}

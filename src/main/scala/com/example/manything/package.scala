package com.example
import cats.data.EitherT

import scala.concurrent.Future

package object manything {
  // type EitherTFuture[A] = EitherT[Future, Throwable, A]
  type EitherAppliedFuture[A] = Future[Either[Throwable, A]]
}

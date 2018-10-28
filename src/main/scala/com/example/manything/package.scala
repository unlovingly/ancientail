package com.example

import scala.concurrent.Future

package object manything {
  // type EitherTFuture[A] = EitherT[Future, Throwable, A]
  type EitherAppliedFuture[A] = Future[Either[Throwable, A]]
}

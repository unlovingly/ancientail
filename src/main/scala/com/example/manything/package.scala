package com.example

import scala.concurrent.Future

package object manything {
  type EitherAppliedFuture[A] = Future[Either[Throwable, A]]
}

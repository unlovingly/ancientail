package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher

import javax.inject.Singleton

import scala.concurrent.{ExecutionContext, Future}

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases

@Singleton
class PublisherController(cc: ControllerComponents,
                          publihserUseCases: PublisherUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher.circe.PublisherCodec._

  def index() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val publishers: EitherTFuture[Seq[Publisher]] =
      publihserUseCases.list()

    val result: Future[Result] = publishers
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Publisher]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val publisher: EitherTFuture[Publisher] =
        publihserUseCases.create(request.body)

      val result: Future[Result] = publisher
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}

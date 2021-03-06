package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher

import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.publisher.{ Publisher, PublisherId }
import com.example.manything.ambientendre.domain.usecases.publisher.PublisherUseCases

@Singleton
class PublisherController(
    cc: ControllerComponents,
    publihserUseCases: PublisherUseCases[EitherTFuture]
)(implicit executionContext: ExecutionContext)
    extends AbstractController(cc)
    with I18nSupport
    with Circe {
  import com.example.manything.ambientendre.outsiders.circe.publisher.PublisherCodec._

  def retrieve() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val publishers =
      publihserUseCases.retrieve()

    val result = publishers
      .fold(
        left => BadRequest(left.toString.asJson.spaces2),
        right => Ok(right.asJson.spaces2)
      )

    result
  }

  def retrieveBy(id: Seq[PublisherId]) =
    Action.async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val publishers =
        publihserUseCases.retrieve(id)

      val result = publishers
        .fold(
          left => BadRequest(left.toString.asJson.spaces2),
          right => Ok(right.asJson.spaces2)
        )

      result
    }

  def performCreation() =
    Action(circe.tolerantJson[Publisher]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val publisher =
        publihserUseCases.create(request.body)

      val result = publisher
        .fold(
          left => BadRequest(left.toString.asJson.spaces2),
          right => Ok(right.asJson.spaces2)
        )

      result
    }
}

package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.publisher.Publisher
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class PublisherController(cc: ControllerComponents,
                          publihserUseCases: PublisherUseCases)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  def index() = Action.async { implicit request: Request[AnyContent] =>
    import io.circe.generic.auto._
    import io.circe.syntax._

    val publishers: EitherAppliedFuture[Seq[Publisher]] =
      publihserUseCases.list()

    val result: Future[Result] = publishers
      .map {
        case Right(r) =>
          r.asJson.spaces2
        case Left(l) => l.toString.asJson.spaces2
      }
      .map { r =>
        Ok(r)
      }

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Publisher]) { implicit request =>
      publihserUseCases.create(request.body)

      Ok("")
    }
}

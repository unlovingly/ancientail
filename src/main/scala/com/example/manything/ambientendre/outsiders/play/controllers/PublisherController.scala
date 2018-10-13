package com.example.manything.ambientendre.outsiders.play.controllers

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.ambientendre.outsiders.play.forms.PublisherIdForm
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import javax.inject._
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.I18nSupport

@Singleton
class PublisherController(cc: ControllerComponents, uc: PublisherUseCases)
  extends AbstractController(cc)
  with I18nSupport {
  lazy val f = Form(
    mapping(
      "identity" -> optional(of[PublisherId](new PublisherIdForm() {})),
      "name" -> nonEmptyText
    )(Publisher.apply)(Publisher.unapply))

  def index() = Action.async { implicit request: Request[AnyContent] =>
    val publishers: Future[Seq[Publisher]] = uc.list(Seq.empty[PublisherId])

    publishers.map(p => Ok(views.html.publisher.index(p)))
  }

  def create() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.publisher.create(f))
  }

  def performCreation() = Action.async { implicit request =>
    f.bindFromRequest.fold(
      e => {
        Future.successful(Ok(views.html.publisher.create(e)))
      },
      p => {
        uc.create(p).map { _ =>
          Redirect(routes.PublisherController.create())
            .flashing("success" -> "publisher.created")
        }
      }
    )
  }
}

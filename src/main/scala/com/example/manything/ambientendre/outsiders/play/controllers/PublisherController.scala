package com.example.manything.ambientendre.outsiders.play.controllers

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import com.example.manything.roundelayout.domain.Identifiability
import javax.inject._
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.i18n.I18nSupport

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class PublisherController(cc: ControllerComponents, uc: PublisherUseCases)
  extends AbstractController(cc)
  with I18nSupport {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val publishers: Future[Seq[Publisher]] = uc.list(Seq.empty[PublisherId])

    publishers.map(p => Ok(views.html.publisher.index(p)))
  }

  def create() = Action { implicit request: Request[AnyContent] =>
    import play.api.data.format.Formats._

    val i =
      (id: String) => Identifiability[UUID, Publisher](UUID.fromString(id))

    val formatter = new Formatter[PublisherId] {
      override def bind(
        key: String,
        data: Map[String, String]): Either[Seq[FormError], PublisherId] = {
        parsing(i, "error.id", Nil)(key, data)
      }
      override def unbind(key: String,
                          value: PublisherId): Map[String, String] = {
        Map(key -> value.value.toString)
      }
    }

    val f = Form(
      mapping(
        "identity" -> optional(of[PublisherId](formatter)),
        "name" -> nonEmptyText
      )(Publisher.apply)(Publisher.unapply))

    Ok(views.html.publisher.create(f))
  }

  def performCreation() = Action { implicit request: Request[AnyContent] =>
    Ok("")
  }
}

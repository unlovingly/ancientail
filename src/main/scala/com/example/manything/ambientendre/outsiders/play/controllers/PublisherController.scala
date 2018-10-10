package com.example.manything.ambientendre.outsiders.play.controllers

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import javax.inject._
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class PublisherController(cc: ControllerComponents, uc: PublisherUseCases)
  extends AbstractController(cc) {

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
    Ok("")
  }
}

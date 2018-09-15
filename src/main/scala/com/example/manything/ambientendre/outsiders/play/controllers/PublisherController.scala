package com.example.manything.ambientendre.outsiders.play.controllers

import com.example.manything.ambientendre.domain.publisher.Publisher
import com.example.manything.roundelayout.usecase.UseCase
import javax.inject._
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class PublisherController(cc: ControllerComponents,
                          usecase: UseCase[Seq[Publisher]])
    extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val publishers: Future[Seq[Publisher]] = usecase.realize()

    publishers.map(p => Ok(views.html.publisher.index(p)))
  }
}

package com.example.manything.ancientail.outsiders.play.controllers

import com.example.manything.ancientail.domain.product.Product
import com.example.manything.roundelayout.usecase.UseCase
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductController(cc: ControllerComponents,
                        usecase: UseCase[Seq[Product]])
  extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val products: Future[Seq[Product]] = usecase.realize()

    products.map(p => Ok(views.html.product.index(p)))
  }
}

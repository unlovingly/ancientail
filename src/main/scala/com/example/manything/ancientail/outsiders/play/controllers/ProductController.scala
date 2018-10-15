package com.example.manything.ancientail.outsiders.play.controllers

import com.example.manything.ambientendre.domain.product.Product
import com.example.manything.ambientendre.usecases.product.ProductUseCases
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import javax.inject._
import play.api.data._
import play.api.i18n.I18nSupport
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ProductController(cc: ControllerComponents,
                        productUseCases: ProductUseCases,
                        publisherUseCases: PublisherUseCases)
  extends AbstractController(cc)
  with I18nSupport {
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val products: Future[Seq[Product]] =
      productUseCases.list()

    products.map(p => Ok(views.html.product.index(p)))
  }

  def create() = Action.async { implicit request: Request[AnyContent] =>
    import com.example.manything.ambientendre.outsiders.play.forms.productForm

    val publishers = publisherUseCases.list()

    publishers.map(p =>
      Ok(views.html.product.create(implicitly[Form[Product]], p)))
  }

  def performCreation() = Action.async { implicit request =>
    import com.example.manything.ambientendre.outsiders.play.forms.productForm

    implicitly[Form[Product]].bindFromRequest.fold(
      e => {
        val publishers = publisherUseCases.list()

        publishers.map { p =>
          BadRequest(views.html.product.create(implicitly[Form[Product]], p))
        }
      },
      p => {
        productUseCases.create(p).map { _ =>
          Redirect(routes.ProductController.create())
            .flashing("success" -> "product.created")
        }
      }
    )
  }
}

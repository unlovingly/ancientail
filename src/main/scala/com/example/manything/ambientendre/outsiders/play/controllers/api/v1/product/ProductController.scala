package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product

import com.example.manything.ambientendre.domain.product.Product
import com.example.manything.ambientendre.usecases.product.ProductUseCases
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import javax.inject._
import play.api.data._
import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ProductController(cc: ControllerComponents,
                        productUseCases: ProductUseCases,
                        publisherUseCases: PublisherUseCases)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val products: Future[Seq[Product]] =
      productUseCases.list()

    products.map { p =>
      import io.circe.generic.auto._
      import io.circe.syntax._

      val j = p.asJson.spaces2

      Ok(j)
    }
  }

  def performCreation() =
    Action(circe.tolerantJson[Product]) { implicit request =>
      Ok("")
    }
}

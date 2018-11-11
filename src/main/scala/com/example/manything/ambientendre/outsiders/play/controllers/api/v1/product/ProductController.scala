package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product

import javax.inject._

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product.Product
import com.example.manything.ambientendre.usecases.product.ProductUseCases
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases

@Singleton
class ProductController(cc: ControllerComponents,
                        productUseCases: ProductUseCases,
                        publisherUseCases: PublisherUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  def index() = Action.async { implicit request: Request[AnyContent] =>
    import io.circe.generic.auto._
    import io.circe.syntax._

    val products: EitherAppliedFuture[Seq[Product]] =
      productUseCases.list()

    products
      .map {
        case Right(r) =>
          r.asJson.spaces2
        case Left(l) => l.toString.asJson.spaces2
      }
      .map { r =>
        Ok(r)
      }
  }

  def performCreation() =
    Action(circe.tolerantJson[Product]) { implicit request =>
      Ok("")
    }
}

package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product

import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

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
  import com.example.manything.ambientendre.outsiders.infrastructure.product.circe.ProductCodec._

  def index() = Action.async { implicit request: Request[AnyContent] =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val products =
      productUseCases.list()

    val result = products
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Product]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val publisher =
        productUseCases.create(request.body)

      val result = publisher
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}

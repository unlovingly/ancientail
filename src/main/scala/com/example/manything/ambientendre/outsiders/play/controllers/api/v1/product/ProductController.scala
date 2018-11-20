package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product

import javax.inject._

import scala.concurrent.{ExecutionContext, Future}

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
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
    import cats.implicits._

    import io.circe.syntax._

    val products: EitherTFuture[Seq[Product]] =
      productUseCases.list()

    val result = products
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Product]).async { implicit request =>
      import cats.implicits._

      import io.circe.syntax._

      val publisher: EitherTFuture[Product] =
        productUseCases.create(request.body)

      val result: Future[Result] = publisher
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}

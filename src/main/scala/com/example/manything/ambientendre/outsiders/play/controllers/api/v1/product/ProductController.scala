package com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product

import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.product.{
  Product,
  ProductId
}
import com.example.manything.ambientendre.domain.usecases.product.ProductUseCases
import com.example.manything.ambientendre.domain.usecases.publisher.PublisherUseCases

@Singleton
class ProductController(cc: ControllerComponents,
                        productUseCases: ProductUseCases[EitherTFuture],
                        publisherUseCases: PublisherUseCases[EitherTFuture])(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  import com.example.manything.ambientendre.outsiders.circe.product.ProductCodec._

  def retrieve() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val products =
      productUseCases.retrieve()

    val result = products
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def retrieveBy(id: Seq[ProductId]) = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val products =
      productUseCases.retrieve(id)

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

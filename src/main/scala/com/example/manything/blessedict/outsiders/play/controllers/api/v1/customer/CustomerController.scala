package com.example.manything.blessedict.outsiders.play.controllers.api.v1.customer

import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.models.customer.Customer
import com.example.manything.blessedict.domain.usecases.customer.CustomerUseCases

@Singleton
class CustomerController(
    cc: ControllerComponents,
    customerUseCases: CustomerUseCases[EitherTFuture]
)(implicit executionContext: ExecutionContext)
    extends AbstractController(cc)
    with I18nSupport
    with Circe {
  import com.example.manything.blessedict.outsiders.circe.CustomerCodec._

  def index() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val customers =
      customerUseCases.retrieve()

    val result = customers
      .fold({
        case _: NoSuchElementException =>
          NotFound("")
        case e =>
          BadRequest(e.toString.asJson.spaces2)
      }, right => Ok(right.asJson.spaces2))

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Customer]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val customer =
        customerUseCases.create(request.body)

      val result = customer
        .fold(
          left => BadRequest(left.toString.asJson.spaces2),
          right => Ok(right.asJson.spaces2)
        )

      result
    }
}

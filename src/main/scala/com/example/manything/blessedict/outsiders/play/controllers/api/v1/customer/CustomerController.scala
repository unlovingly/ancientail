package com.example.manything.blessedict.outsiders.play.controllers.api.v1.customer

import javax.inject.Singleton

import scala.concurrent.{ExecutionContext, Future}

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.customer.Customer
import com.example.manything.blessedict.domain.usecases.customer.CustomerUseCases

@Singleton
class CustomerController(cc: ControllerComponents,
                         publihserUseCases: CustomerUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  import com.example.manything.blessedict.outsiders.circe.CustomerCodec._

  def index() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val customers =
      publihserUseCases.retrieve()

    val result = customers
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Customer]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val customer =
        publihserUseCases.create(request.body)

      val result = customer
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}

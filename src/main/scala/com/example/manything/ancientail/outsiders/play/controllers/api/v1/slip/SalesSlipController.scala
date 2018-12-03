package com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip

import java.util.UUID
import javax.inject.Singleton

import scala.concurrent.{ExecutionContext, Future}

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.ancientail.domain.slip.sales.SalesSlip
import com.example.manything.ancientail.usecases.slip.SalesSlipUseCases

@Singleton
class SalesSlipController(
  cc: ControllerComponents,
  slipUseCases: SalesSlipUseCases)(implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  import com.example.manything.ancientail.outsiders.infrastructure.slip.circe.SlipCodec._

  // TODO
  val shopId: ShopId = ShopId(new UUID(0, 0))

  def index() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val slips: EitherTFuture[Seq[SalesSlip]] =
      slipUseCases.retrieve()

    val result = slips
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def detail(id: SlipId) =
    Action.async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val slips: EitherTFuture[SalesSlip] =
        slipUseCases.retrieve(shopId, id)

      val result: Future[Result] = slips
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }

  def sales() =
    Action(circe.tolerantJson[SalesSlip]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val slip: EitherTFuture[SalesSlip] =
        slipUseCases.sell(request.body)

      val result: Future[Result] = slip
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}
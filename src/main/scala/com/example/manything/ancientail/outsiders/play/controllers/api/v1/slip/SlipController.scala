package com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip

import java.util.UUID
import javax.inject._

import scala.concurrent.{ExecutionContext, Future}

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.usecases.slip.SlipUseCases

@Singleton
class SlipController(cc: ControllerComponents, slipUseCases: SlipUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  // TODO
  val shopId: ShopId = ShopId(new UUID(0, 0))

  def detail(id: SlipId) =
    Action.async { implicit request =>
      import cats.implicits._

      import io.circe.syntax._

      val slips: EitherTFuture[SlipBase] =
        slipUseCases.retrieve(shopId, id)

      val result: Future[Result] = slips
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.toString.asJson.spaces2))

      result
    }

  def exchange() =
    Action(circe.tolerantJson[ExchangeSlip]).async { implicit request =>
      import cats.implicits._

      import io.circe.syntax._

      val slip =
        slipUseCases.exchange(request.body)

      val result: Future[Result] = slip
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.toString.asJson.spaces2))

      result
    }

  def storing() =
    Action(circe.tolerantJson[PurchaseSlip]).async { implicit request =>
      import cats.implicits._

      import io.circe.syntax._

      val slip: EitherTFuture[PurchaseSlip] =
        slipUseCases.storing(request.body)

      val result: Future[Result] = slip
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.toString.asJson.spaces2))

      result
    }
}

package com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip

import java.util.UUID

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.usecases.slip.SlipUseCases
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

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
      val slips: EitherAppliedFuture[SlipBase] =
        slipUseCases.retrieve(shopId, id)

      eitherToResult(slips)
    }

  def exchange() =
    Action(circe.tolerantJson[ExchangeSlip]).async { implicit request =>
      val result =
        slipUseCases.exchange(request.body)

      eitherToResult(result)
    }

  def storing() =
    Action(circe.tolerantJson[PurchaseSlip]).async { implicit request =>
      val result: EitherAppliedFuture[SlipBase] =
        slipUseCases.storing(request.body)

      eitherToResult(result)
    }

  private def eitherToResult(
    e: EitherAppliedFuture[SlipBase]): Future[Result] = {
    import io.circe.syntax._

    e.map {
      case Right(r) => Ok(r.asJson.spaces2)
      case Left(l) => BadRequest(l.toString.asJson.spaces2)
    }
  }
}

package com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip

import java.util.UUID

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.usecases.slip.SlipUseCases
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class SlipController(cc: ControllerComponents, slipUseCases: SlipUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  // TODO
  val shopId: ShopId = ShopId(new UUID(0, 0))

  def detail(id: SlipId) = Action.async {
    implicit request: Request[AnyContent] =>
      import io.circe.syntax._

      val slips: EitherAppliedFuture[SlipBase] =
        slipUseCases.retrieve(shopId, id)

      slips
        .map {
          case Right(r) =>
            Ok(r.asJson.spaces2)
          case Left(l) => BadRequest(l.toString.asJson.spaces2)
        }
  }

  def performCreation() =
    Action(circe.tolerantJson[PurchaseSlip]).async { implicit request =>
      import io.circe.syntax._

      val result =
        slipUseCases.storing(request.body)

      result.map {
        case Right(r) => Ok(r.asJson.spaces2)
        case Left(l) => BadRequest(l.toString)
      }
    }

  def storing() =
    Action(circe.tolerantJson[PurchaseSlip]).async { implicit request =>
      import io.circe.syntax._

      val result =
        slipUseCases.storing(request.body)

      result.map {
        case Right(r) => Ok(r.asJson.spaces2)
        case Left(l) => BadRequest(l.toString)
      }
    }
}

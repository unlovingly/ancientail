package com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip

import java.util.UUID

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.{Slip, SlipId}
import com.example.manything.ancientail.usecases.slip.SlipUseCases
import com.example.manything.roundelayout.domain.Identifiability
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class SlipController(cc: ControllerComponents, slipUseCases: SlipUseCases)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  def detail(id: SlipId) = Action.async {
    implicit request: Request[AnyContent] =>
      import io.circe.generic.auto._
      import io.circe.syntax._

      val slips: EitherAppliedFuture[Seq[Slip]] =
        slipUseCases.retrieve(Identifiability(new UUID(0, 0)), id)

      slips
        .map {
          case Right(r) =>
            Ok(r.asJson.spaces2)
          case Left(l) => BadRequest(l.toString.asJson.spaces2)
        }
  }

  def performCreation() =
    Action(circe.tolerantJson[Slip]) { implicit request =>
      slipUseCases.storing(Identifiability(new UUID(0, 0)), request.body)

      Ok("")
    }
}

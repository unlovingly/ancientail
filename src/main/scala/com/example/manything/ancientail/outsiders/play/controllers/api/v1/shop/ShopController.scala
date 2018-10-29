package com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.Shop
import com.example.manything.ancientail.usecases.shop.ShopUseCases
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ShopController(cc: ControllerComponents, shopUseCases: ShopUseCases)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  def index() = Action.async { implicit request: Request[AnyContent] =>
    import io.circe.generic.auto._
    import io.circe.syntax._

    val shops: EitherAppliedFuture[Seq[Shop]] =
      shopUseCases.list()

    shops
      .map {
        case Right(r) =>
          r.asJson.spaces2
        case Left(l) => l.toString.asJson.spaces2
      }
      .map { r =>
        Ok(r)
      }
  }

  def performCreation() =
    Action(circe.tolerantJson[Shop]).async { implicit request =>
      val result: EitherAppliedFuture[Shop] =
        shopUseCases.create(request.body)

      result.map {
        case Right(r) => Ok(r.name)
        case Left(l) => BadRequest(l.toString)
      }
    }
}

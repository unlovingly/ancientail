package com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop

import javax.inject._

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.Shop
import com.example.manything.ancientail.usecases.shop.ShopUseCases

@Singleton
class ShopController(cc: ControllerComponents, shopUseCases: ShopUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  def index() = Action.async { implicit request =>
    import cats.implicits._

    import io.circe.syntax._

    val shops: EitherTFuture[Seq[Shop]] =
      shopUseCases.list()

    val result = shops
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.toString.asJson.spaces2))

    result
  }

  def performCreation() =
    Action(circe.tolerantJson[Shop]).async { implicit request =>
      import cats.implicits._

      import io.circe.syntax._

      val shop: EitherTFuture[Shop] =
        shopUseCases.create(request.body)

      val result = shop
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.toString.asJson.spaces2))

      result
    }
}

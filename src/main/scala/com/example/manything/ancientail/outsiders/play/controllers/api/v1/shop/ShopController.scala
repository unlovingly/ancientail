package com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop

import java.util.UUID
import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.ancientail.domain.models.shop._
import com.example.manything.ancientail.domain.usecases.shop.ShopUseCases
import com.example.manything.outsiders.slick.NotFoundException

@Singleton
class ShopController(cc: ControllerComponents,
                     protected val shopUseCases: ShopUseCases)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  import com.example.manything.ancientail.outsiders.infrastructure.shop.circe.ShopCodec._

  val shopId: ShopId = ShopId(new UUID(0, 0))

  def retrieveWithStocks() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val shops =
      shopUseCases.retrieveWithStocksBy(shopId)

    val result = shops
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

  def retrieveWithStocksByQuery(q: String) = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val shops =
      shopUseCases.retrieveWithStocksBy(q)

    val result = shops
      .fold({
        case NotFoundException(e) =>
          NotFound(e.asJson.spaces2)
        case e =>
          BadRequest(e.toString.asJson.spaces2)
      }, right => Ok(right.asJson.spaces2))

    result
  }

  def retrieveWithStocksByCode(code: PluCode) = Action.async {
    implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val shops =
        shopUseCases.retrieveWithStocksBy(shopId, code)

      val result = shops
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
  }

  def performCreation() =
    Action(circe.tolerantJson[Shop]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val shop =
        shopUseCases.create(request.body)

      val result = shop
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}

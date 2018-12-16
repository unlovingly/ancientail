package com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop

import java.util.UUID
import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.shop._
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlip
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlip
import com.example.manything.ancientail.domain.usecases.shop.ShopUseCases

@Singleton
class ShopController(cc: ControllerComponents,
                     protected val shopUseCases: ShopUseCases[EitherTFuture])(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport
  with Circe {
  import com.example.manything.ancientail.outsiders.circe.shop.ShopCodec._
  import com.example.manything.ancientail.outsiders.circe.slip.SlipCodec._

  val shopId: ShopId = ShopId(new UUID(0, 0))

  def detail(shopId: ShopId) = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val shops =
      shopUseCases.retrieve(shopId)

    val result = shops
      .fold(left => BadRequest(left.toString.asJson.spaces2),
            right => Ok(right.asJson.spaces2))

    result
  }

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
        case _: NoSuchElementException =>
          NotFound("")
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

  def sell() =
    Action(circe.tolerantJson[SalesSlip]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val slip =
        shopUseCases.sell(request.body)

      val result = slip
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }

  def store() =
    Action(circe.tolerantJson[PurchaseSlip]).async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val slip =
        shopUseCases.store(request.body)

      val result = slip
        .fold(left => BadRequest(left.toString.asJson.spaces2),
              right => Ok(right.asJson.spaces2))

      result
    }
}

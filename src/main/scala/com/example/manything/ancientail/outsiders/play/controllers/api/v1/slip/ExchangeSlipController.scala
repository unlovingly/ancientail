package com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip

import java.util.UUID
import javax.inject.Singleton

import scala.concurrent.ExecutionContext

import play.api.i18n.I18nSupport
import play.api.libs.circe.Circe
import play.api.mvc._

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.usecases.slip.ExchangeSlipUseCases

@Singleton
class ExchangeSlipController(
    cc: ControllerComponents,
    slipUseCases: ExchangeSlipUseCases[EitherTFuture]
)(implicit executionContext: ExecutionContext)
    extends AbstractController(cc)
    with I18nSupport
    with Circe {
  import com.example.manything.ancientail.outsiders.circe.slip.SlipCodec._

  // TODO
  val shopId: ShopId = ShopId(new UUID(0, 0))

  def index() = Action.async { implicit request =>
    import cats.implicits.catsStdInstancesForFuture

    import io.circe.syntax.EncoderOps

    val slips =
      slipUseCases.retrieve()

    val result = slips
      .fold(
        left => BadRequest(left.toString.asJson.spaces2),
        right => Ok(right.asJson.spaces2)
      )

    result
  }

  def detail(id: SlipId) =
    Action.async { implicit request =>
      import cats.implicits.catsStdInstancesForFuture

      import io.circe.syntax.EncoderOps

      val slips =
        slipUseCases.retrieve(shopId, id)

      val result = slips
        .fold(
          left => BadRequest(left.toString.asJson.spaces2),
          right => Ok(right.asJson.spaces2)
        )

      result
    }
}

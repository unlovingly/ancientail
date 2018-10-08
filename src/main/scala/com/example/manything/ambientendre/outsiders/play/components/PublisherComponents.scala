package com.example.manything.ambientendre.outsiders.play.components

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherRepository
}
import com.example.manything.ambientendre.outsiders.infrastructure.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.play.controllers.PublisherController
import com.example.manything.ambientendre.usecases.publisher.ListingPublishers
import com.example.manything.outsiders.play.components.{
  ControllerComponents,
  OutsiderComponents
}
import com.example.manything.roundelayout.usecase.UseCase
import play.api.BuiltInComponentsFromContext

import scala.concurrent.Future

/**
 * Publisher リポジトリや Publisher コントローラーに必要な依存オブジェクトを宣言する
 */
trait PublisherComponents extends ControllerComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val repository: PublisherRepository[Future] =
    new PublisherRepositoryWithSlick()
  lazy val listingPublishers: UseCase[Unit, Seq[Publisher]] =
    new ListingPublishers(repository)

  lazy val publisherController = new PublisherController(
    cc = controllerComponents,
    usecase = listingPublishers)
  lazy val publisherRoutes =
    new publishers.Routes(httpErrorHandler, publisherController)
}

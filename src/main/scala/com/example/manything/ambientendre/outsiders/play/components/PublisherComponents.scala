package com.example.manything.ambientendre.outsiders.play.components

import com.example.manything.ambientendre.domain.publisher.PublisherRepository
import com.example.manything.ambientendre.outsiders.infrastructure.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.PublisherController
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents
import play.api.BuiltInComponentsFromContext

import scala.concurrent.Future

/**
 * Publisher リポジトリや Publisher コントローラーに必要な依存オブジェクトを宣言する
 */
trait PublisherComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val repository: PublisherRepository[Future] =
    new PublisherRepositoryWithSlick()
  private lazy val publisherUseCases = new PublisherUseCases()

  lazy val publisherController =
    new PublisherController(cc = controllerComponents, uc = publisherUseCases)
  lazy val publisherRoutes =
    new publishers.Routes(httpErrorHandler, publisherController)
}

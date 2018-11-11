package com.example.manything.ambientendre.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.publisher.PublisherRepository
import com.example.manything.ambientendre.outsiders.infrastructure.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.PublisherController
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents

/**
 * Publisher リポジトリや Publisher コントローラーに必要な依存オブジェクトを宣言する
 */
trait PublisherComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val repository: PublisherRepository[EitherTFuture] =
    new PublisherRepositoryWithSlick()
  private lazy val publisherUseCases = new PublisherUseCases()

  lazy val publisherController =
    new PublisherController(
      cc = controllerComponents,
      publihserUseCases = publisherUseCases)(executionContext)
  lazy val publisherRoutes =
    new publishers.Routes(httpErrorHandler, publisherController)
}

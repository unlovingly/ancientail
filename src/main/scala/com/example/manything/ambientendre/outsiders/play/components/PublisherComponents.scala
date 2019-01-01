package com.example.manything.ambientendre.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.publisher.PublisherRepository
import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.publisher.PublisherController
import com.example.manything.ambientendre.outsiders.slick.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.domain.usecases.publisher.PublisherUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents

trait PublisherComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val repository: PublisherRepository[EitherTFuture] =
    new PublisherRepositoryWithSlick(db = db)
  private lazy val publisherUseCases = new PublisherUseCases(
    publishers = repository)

  lazy val publisherController =
    new PublisherController(
      cc = controllerComponents,
      publihserUseCases = publisherUseCases)(executionContext)
  lazy val publisherRoutes =
    new publishers.Routes(httpErrorHandler, publisherController)
}

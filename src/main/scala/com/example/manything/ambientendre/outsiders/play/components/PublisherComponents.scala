package com.example.manything.ambientendre.outsiders.play.components

import com.example.manything.ambientendre.domain.publisher.{Publisher, PublisherRepository}
import com.example.manything.ambientendre.outsiders.infrastructure.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.play.controllers.PublisherController
import com.example.manything.ambientendre.usecases.publisher.ListingPublishers
import com.example.manything.roundelayout.usecase.UseCase
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.mvc.DefaultControllerComponents
import router.Routes

class PublisherComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with OutsiderComponents
    with controllers.AssetsComponents {
  override lazy val controllerComponents = DefaultControllerComponents(
    defaultActionBuilder,
    playBodyParsers,
    messagesApi,
    langs,
    fileMimeTypes,
    executionContext
  )

  lazy val repository: PublisherRepository = new PublisherRepositoryWithSlick()
  lazy val listingPublishers: UseCase[Publisher] = new ListingPublishers(repository)

  lazy val publisherController = new PublisherController(cc = controllerComponents, usecase = listingPublishers)
  lazy val routes = new publishers.Routes(httpErrorHandler, publisherController)
  lazy override val router = new Routes(httpErrorHandler, routes, assets)
}

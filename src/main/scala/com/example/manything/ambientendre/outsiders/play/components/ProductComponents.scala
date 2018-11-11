package com.example.manything.ambientendre.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherAppliedFuture
import com.example.manything.ambientendre.domain.product.ProductRepository
import com.example.manything.ambientendre.domain.publisher.PublisherRepository
import com.example.manything.ambientendre.outsiders.infrastructure.product.ProductRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.infrastructure.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.ProductController
import com.example.manything.ambientendre.usecases.product.ProductUseCases
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents

trait ProductComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val productRepository
    : ProductRepository[EitherAppliedFuture] =
    new ProductRepositoryWithSlick()
  implicit private lazy val publisherRepository
    : PublisherRepository[EitherAppliedFuture] =
    new PublisherRepositoryWithSlick()
  private lazy val productUseCases =
    new ProductUseCases()
  private lazy val publisherUseCases =
    new PublisherUseCases()

  lazy val productController =
    new ProductController(
      cc = controllerComponents,
      productUseCases = productUseCases,
      publisherUseCases = publisherUseCases)(executionContext)
  lazy val productRoutes =
    new products.Routes(httpErrorHandler, productController)
}

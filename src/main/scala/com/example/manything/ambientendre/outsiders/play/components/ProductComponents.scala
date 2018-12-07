package com.example.manything.ambientendre.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.ambientendre.domain.models.product.ProductRepository
import com.example.manything.ambientendre.domain.models.publisher.PublisherRepository
import com.example.manything.ambientendre.outsiders.play.controllers.api.v1.product.ProductController
import com.example.manything.ambientendre.outsiders.slick.product.ProductRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.slick.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.domain.usecases.product.ProductUseCases
import com.example.manything.ambientendre.domain.usecases.publisher.PublisherUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents

trait ProductComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val productRepository: ProductRepository[EitherTFuture] =
    new ProductRepositoryWithSlick(db = db)
  private lazy val publisherRepository: PublisherRepository[EitherTFuture] =
    new PublisherRepositoryWithSlick(db = db)
  private lazy val productUseCases =
    new ProductUseCases(products = productRepository)
  private lazy val publisherUseCases =
    new PublisherUseCases(publishers = publisherRepository)

  lazy val productController =
    new ProductController(
      cc = controllerComponents,
      productUseCases = productUseCases,
      publisherUseCases = publisherUseCases)(executionContext)
  lazy val productRoutes =
    new products.Routes(httpErrorHandler, productController)
}

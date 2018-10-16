package com.example.manything.ambientendre.outsiders.play.components
import com.example.manything.ambientendre.domain.product.ProductRepository
import com.example.manything.ambientendre.domain.publisher.PublisherRepository
import com.example.manything.ambientendre.outsiders.infrastructure.product.ProductRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.infrastructure.publisher.PublisherRepositoryWithSlick
import com.example.manything.ambientendre.outsiders.play.controllers.product.ProductController
import com.example.manything.ambientendre.usecases.product.ProductUseCases
import com.example.manything.ambientendre.usecases.publisher.PublisherUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents
import play.api.BuiltInComponentsFromContext

import scala.concurrent.Future

trait ProductComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val productRepository: ProductRepository[Future] =
    new ProductRepositoryWithSlick()
  implicit private lazy val publisherRepository: PublisherRepository[Future] =
    new PublisherRepositoryWithSlick()
  private lazy val productUseCases =
    new ProductUseCases()
  private lazy val publisherUseCases =
    new PublisherUseCases()

  lazy val productController =
    new ProductController(cc = controllerComponents,
                          productUseCases = productUseCases,
                          publisherUseCases = publisherUseCases)
  lazy val productRoutes =
    new products.Routes(httpErrorHandler, productController)
}

package com.example.manything.ancientail.outsiders.play.components

import com.example.manything.ancientail.domain.product.{
  Product,
  ProductRepository
}
import com.example.manything.ancientail.outsiders.infrastructure.product.ProductRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.ProductController
import com.example.manything.ancientail.usecases.product.{
  ListingProducts,
  ProductUseCases
}
import com.example.manything.outsiders.play.components.{
  ControllerComponents,
  OutsiderComponents
}
import play.api.BuiltInComponentsFromContext

import scala.concurrent.Future

trait ProductComponents extends ControllerComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val repository: ProductRepository[Future] =
    new ProductRepositoryWithSlick()
  lazy val listingProducts =
    new ProductUseCases()

  lazy val productController =
    new ProductController(cc = controllerComponents, uc = listingProducts)
  lazy val productRoutes =
    new products.Routes(httpErrorHandler, productController)
}

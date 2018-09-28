package com.example.manything.ancientail.outsiders.play.components

import com.example.manything.ancientail.domain.product.{
  Product,
  ProductRepository
}
import com.example.manything.ancientail.outsiders.infrastructure.product.ProductRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.ProductController
import com.example.manything.ancientail.usecases.product.ListingProducts
import com.example.manything.outsiders.play.components.{
  ControllerComponents,
  OutsiderComponents
}
import com.example.manything.roundelayout.usecase.UseCase
import play.api.BuiltInComponentsFromContext

import scala.concurrent.Future

trait ProductComponents extends ControllerComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val repository: ProductRepository[Future] =
    new ProductRepositoryWithSlick()
  lazy val listingProducts: UseCase[Seq[Product], Future] =
    new ListingProducts(repository)

  lazy val productController =
    new ProductController(cc = controllerComponents, usecase = listingProducts)
  lazy val productRoutes =
    new products.Routes(httpErrorHandler, productController)
}

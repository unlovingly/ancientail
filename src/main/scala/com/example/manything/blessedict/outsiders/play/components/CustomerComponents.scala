package com.example.manything.blessedict.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.blessedict.domain.models.customer.CustomerRepository
import com.example.manything.blessedict.domain.usecases.customer.CustomerUseCases
import com.example.manything.blessedict.outsiders.play.controllers.api.v1.customer.CustomerController
import com.example.manything.blessedict.outsiders.slick.CustomerRepositoryWithSlick
import com.example.manything.outsiders.play.components.OutsiderComponents

/**
 * Customer リポジトリや Customer コントローラーに必要な依存オブジェクトを宣言する
 */
trait CustomerComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val repository: CustomerRepository[EitherTFuture] =
    new CustomerRepositoryWithSlick(db = db)
  private lazy val customerUseCases = new CustomerUseCases(
    customers = repository)

  lazy val customerController =
    new CustomerController(
      cc = controllerComponents,
      publihserUseCases = customerUseCases)(executionContext)
  lazy val customerRoutes =
    new customers.Routes(httpErrorHandler, customerController)
}

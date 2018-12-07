package com.example.manything.ancientail.domain.usecases.shop

trait TakeInventory[A[_]] { this: ShopUseCases[A] =>
  def inventory(): A[Unit] =
    shops.inventory("aa")
}

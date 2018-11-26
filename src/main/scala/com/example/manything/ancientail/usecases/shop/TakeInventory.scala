package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherTFuture

trait TakeInventory { this: ShopUseCases =>
  def inventory(): EitherTFuture[Unit] =
    shops.inventory("aa")
}

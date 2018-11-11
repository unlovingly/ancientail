package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherAppliedFuture

trait TakeInventory { this: ShopUseCases =>
  def inventory(): EitherAppliedFuture[Unit] =
    shops.inventory("aa")
}

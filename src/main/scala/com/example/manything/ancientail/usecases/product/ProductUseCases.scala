package com.example.manything.ancientail.usecases.product
import com.example.manything.ancientail.domain.product.ProductRepository

import scala.concurrent.Future

class ProductUseCases(implicit val products: ProductRepository[Future])
  extends ListingProducts

package com.example.manything.ambientendre.outsiders

import com.example.manything.ambientendre.{domain, usecases}

abstract class ProductService extends usecases.ProductService {
}

object ProductService extends MixinProductRepository

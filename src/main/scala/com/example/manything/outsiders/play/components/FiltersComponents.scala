package com.example.manything.outsiders.play.components

import play.api.mvc.EssentialFilter
import play.filters.HttpFiltersComponents
import play.filters.cors.CORSComponents

trait FiltersComponents extends HttpFiltersComponents with CORSComponents {
  override def httpFilters: Seq[EssentialFilter] =
    super.httpFilters :+ corsFilter
}

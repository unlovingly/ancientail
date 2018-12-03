package com.example.manything.outsiders.slick

case class NotFoundException(m: String = "there is nothing here")
  extends Exception(m)

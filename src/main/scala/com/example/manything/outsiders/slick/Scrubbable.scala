package com.example.manything.outsiders.slick

trait Scrubbable[A, B] {
  def scrub(): A
  def improve(a: A): B
}

package com.example.manything.ambientendre.outsiders.play

import play.api.{Application, ApplicationLoader => Loader}
import play.api.ApplicationLoader.Context

import com.example.manything.ambientendre.outsiders.play.components.PublisherComponents

class ApplicationLoader extends Loader {
  override def load(context: Context): Application =
    new PublisherComponents(context).application
}

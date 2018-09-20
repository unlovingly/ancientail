package com.example.manything.outsiders.play

import com.example.manything.outsiders.play.components.ApplicationComponents
import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader => Loader}

class ApplicationLoader extends Loader {
  override def load(context: Context): Application =
    new ApplicationComponents(context).application
}

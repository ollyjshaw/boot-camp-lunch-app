package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}

class WelcomeController extends Controller{
  def welcome() = Action {
    Ok("HI").as(HTML)
  }
}
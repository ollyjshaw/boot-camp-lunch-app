package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import service.GreetingService

class SandwichController extends Controller{
  def welcome() = Action {
    Ok(views.html.sandwiches())
  }
}

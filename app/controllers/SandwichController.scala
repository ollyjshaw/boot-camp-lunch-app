package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import service.{GreetingService, SandwichService}

class SandwichController @Inject()(sandwichService: SandwichService) extends Controller{
  def sandwiches() = Action {
    val sandwiches = sandwichService.sandwiches()
    Ok(views.html.sandwiches(sandwiches))
  }
}

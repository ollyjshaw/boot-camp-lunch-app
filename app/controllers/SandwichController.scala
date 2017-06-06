package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import service.SandwichService

//default execution context https://www.playframework.com/documentation/2.5.x/ScalaAsync
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class SandwichController @Inject()(sandwichService: SandwichService) extends Controller {
  def sandwiches() = Action.async {
    val sandwiches = sandwichService.sandwiches()
    sandwiches.map{ listOfSarnies =>
      Ok(views.html.sandwiches(listOfSarnies))
    }
  }
}

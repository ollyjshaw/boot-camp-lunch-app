package service

import com.google.inject.ImplementedBy
import models.Sandwich

import scala.concurrent.Future

//default execution context https://www.playframework.com/documentation/2.5.x/ScalaAsync
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class RealSandwichService extends SandwichService {
  //just an empty list
  override def sandwiches(): Future[List[Sandwich]] = Future(List())
}

@ImplementedBy(classOf[RealSandwichService])
trait SandwichService {
  def sandwiches() : Future[List[Sandwich]]
}

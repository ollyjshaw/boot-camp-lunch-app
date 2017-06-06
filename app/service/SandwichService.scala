package service

import com.google.inject.ImplementedBy
import models.Sandwich
import uk.gov.hmrc.play.http.HeaderCarrier
import uk.gov.hmrc.play.http.ws.WSGet

import scala.concurrent.Future

class RealSandwichService extends SandwichService {

  val http = new WSGet {
    override val hooks = NoneRequired
  }

  override def sandwiches: Future[List[Sandwich]] = {
    implicit val hc = HeaderCarrier()
    http.GET[List[Sandwich]]("http://localhost:3000/sandwiches")
  }

}

@ImplementedBy(classOf[RealSandwichService])
trait SandwichService {
  def sandwiches() : Future[List[Sandwich]]
}

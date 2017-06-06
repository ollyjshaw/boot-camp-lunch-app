package controllers

import models.Sandwich
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}
import service.SandwichService
import play.api.inject.bind

//default execution context https://www.playframework.com/documentation/2.5.x/ScalaAsync
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class SandwichControllerSpec extends PlaySpec with GuiceOneAppPerTest {
  "SandwichController" should {
    "Have some basic information and be accessible at the correct route" in {

      val application = new GuiceApplicationBuilder().
        overrides(bind[SandwichService].to[IntegrationSandwichService]).
        build

      // Need to specify Host header to get through AllowedHostsFilter
      val request = FakeRequest(GET, "/sandwiches").withHeaders("Host" -> "localhost")
      val home = route(application, request).get

      //sanitation
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("<title>Sandwiches</title>")
      contentAsString(home) must include("<h1>Have a look at today's sandwiches</h1>")
    }

    "give a helpful message when sold out" in {
      val controller = new SandwichController(FakeNoSandwichService)
      val result = controller.sandwiches().apply(FakeRequest())
      contentAsString(result) must include("<p>Sorry, we're sold out</p>")
    }

    "show a single sandwich when only one is available" in {
      val controller = new SandwichController(FakeSingleSandwichService)
      val result = controller.sandwiches().apply(FakeRequest())

      contentAsString(result) must not include("<p>Sorry, we're sold out</p>")
      contentAsString(result) must include ("Ham")
      contentAsString(result) must include ("Very tasty")
      contentAsString(result) must include ("£1.55")
    }

    "show a multiple sandwiches" in {
      val controller = new SandwichController(FakeMultiSandwichService)
      val result = controller.sandwiches().apply(FakeRequest())

      contentAsString(result) must not include("<p>Sorry, we're sold out</p>")
      contentAsString(result) must include ("Ham")
      contentAsString(result) must include ("Very tasty")
      contentAsString(result) must include ("£1.55")
      contentAsString(result) must include ("Cheese")
      contentAsString(result) must include ("Cheese tastic")
      contentAsString(result) must include ("£2.55")
      contentAsString(result) must include ("Egg")
      contentAsString(result) must include ("Fresh")
      contentAsString(result) must include ("£1.15")
    }
  }
}

class IntegrationSandwichService extends SandwichService {
  override def sandwiches(): Future[List[Sandwich]] = Future(List())
}
object FakeNoSandwichService extends SandwichService {
  override def sandwiches(): Future[List[Sandwich]] = Future(List())
}

object FakeSingleSandwichService extends SandwichService {
  override def sandwiches(): Future[List[Sandwich]] = Future(List(Sandwich("Ham", 1.55, "Very tasty")))
}

object FakeMultiSandwichService extends SandwichService {
  val ham = Sandwich("Ham", 1.55, "Very tasty")
  val cheese = Sandwich("Cheese", 2.55, "Cheese tastic")
  val egg = Sandwich("Egg", 1.15, "Fresh")
  override def sandwiches(): Future[List[Sandwich]] = Future(List(ham, cheese, egg))
}

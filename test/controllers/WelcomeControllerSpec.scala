package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}
import service.GreetingService

object FakeMorningGreeter extends GreetingService {
  override def greeting: String = "Good morning!"
}

object FakeAfternoonGreeter extends GreetingService {
  override def greeting: String = "Good afternoon!"
}

class WelcomeControllerSpec extends PlaySpec with GuiceOneAppPerTest {
  "WelcomeController GET" should {
    "return a successful response" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
      status(result) mustBe OK
    }
    "respond to the /welcome url" in {
      // Need to specify Host header to get through AllowedHostsFilter
      val request = FakeRequest(GET, "/welcome").withHeaders("Host" -> "localhost")
      val home = route(app, request).get
      status(home) mustBe OK
    }
    "return some html" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
      contentType(result) mustBe Some("text/html")
    }
    "say good morning and have a title" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
      contentAsString(result) must include ("<h1>Good morning!</h1>")
      contentAsString(result) must include ("<title>Welcome!</title>")
    }
    "say good afternoon and have a title" in {
      val controller = new WelcomeController(FakeAfternoonGreeter)
      val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
      contentAsString(result) must include ("<h1>Good afternoon!</h1>")
      contentAsString(result) must include ("<title>Welcome!</title>")
    }
  }
}

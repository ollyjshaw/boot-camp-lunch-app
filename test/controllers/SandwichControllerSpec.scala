package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}

class SandwichControllerSpec extends PlaySpec with GuiceOneAppPerTest {
  "SandwichController" should {
    "inform the user we're sold out when there are no sandwiches" in {
      // Need to specify Host header to get through AllowedHostsFilter
      val request = FakeRequest(GET, "/sandwiches").withHeaders("Host" -> "localhost")
      val home = route(app, request).get

      //sanitation
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("<title>Sandwiches</title>")
      contentAsString(home) must include("<h1>Have a look at today's sandwiches</h1>")

      //sandwich behaviour
      contentAsString(home) must include("<p>Sorry, we're sold out</p>")
    }
  }
}

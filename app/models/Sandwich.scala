package models

import play.api.libs.json.{Format, Json}

case class Sandwich(name: String, price: BigDecimal, description: String)

object Sandwich {
  implicit val formats: Format[Sandwich] = Json.format[Sandwich]
}

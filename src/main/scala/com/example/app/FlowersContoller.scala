package com.example.app

import org.scalatra._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class FlowersController extends ScalatraServlet with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal

  case class Flower(slug: String, name: String)

  object FlowerData {

    /**
     * Some fake flowers data so we can simulate retrievals.
     */

    var all = List(
      Flower("yellow-tulip", "Yellow Tulip"),
      Flower("red-rose", "Red Rose"),
      Flower("black-rose", "Black Rose"))
  }

    // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  get("/"){
    FlowerData.all
  }

// get("/flowers"){
//   FlowerData.all
// }

}
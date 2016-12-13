package com.example.app

import org.scalatra._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

import org.slf4j.{Logger, LoggerFactory}
import org.scalatra

// JSON handling support from Scalatra
import org.scalatra.json._
import scala.collection.immutable.Map
import com.example.app._

class GraphController extends MyScalatraWebAppStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal

  var Input: String = _

  post("/toTikz") {
    try {
      Input = request.body
      val toTikz = new JsonToTikz(Input);
      // status OK 200
      "OK"
    }
    catch {
      case e : Exception => e.printStackTrace()
    }
  }

  get("/draw") {
    redirect("/js/script.js")
  }

  get("/downloadTikz"){
    response.setHeader("Content-Disposition","attachment; filename=tikz.txt");
    val toTikz = new JsonToTikz(Input);
    toTikz.TikzText
  }
}

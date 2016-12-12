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

  // get("/graph"){

  //   contentType = formats("json")

  //   GraphData.all
  // }

//  before {
//    contentType = "text/html"
//  }
  

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

  get("/") {
    <html>
      <body>

        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.

      </body>
    </html>
  }

  get("/throw"){
    throw new Exception("John walks his gik.")
  }

  get("/draw") {
    redirect("/js/script.js")
  }

  // produce a simple HTML form
  get("/form") {
    <form action='/post' method='POST'>
      Post something: <input name='submission' type='text'/>
      <input type='submit'/>
    </form>
  }

  // handle POSTs from the form generated above
  post("/post") {
    <h1>You posted: {params("submission")}</h1>
  }

  // respond to '/' with a greeting
  get("/hello") {
    <h1>Hello world!</h1>
  }

  // send redirect headers
  get("/see_ya") {
    redirect("http://google.com")
  }


  get("/downloadTikz"){

    response.setHeader("Content-Disposition","attachment; filename=tikz.txt");

    val toTikz = new JsonToTikz(Input);
    println(toTikz.TikzText)

    toTikz.TikzText


  }

  // Actions that return byte arrays render a binary response
  // get("/report.pdf") {
  //   contentType = "application/pdf"
  //   val pdf = generatePdf()
  //   pdf.toBytes
  // }

  // enable hello-scalate
  // notFound {
  //   response.setStatus(404)
  //   "Not found"
  // }

}

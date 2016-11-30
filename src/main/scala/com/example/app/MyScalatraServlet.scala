package com.example.app

import org.scalatra._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._



class MyScalatraServlet extends MyScalatraWebAppStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal

  case class Node(id: Int, title: String, x: Int, y: Int)

                                          // solves the problem of
                                          // rigid class
                                          // options: Map[String, String]
  case class Edge(source: Int, target: Int)
  // case class Edge(attributes: Map[String, String])

  case class Graph(nodes: List[Node], edges: List[Edge])

  object GraphData {

  /**
   * Some fake flowers data so we can simulate retrievals.
   */

    var all = Graph(
                    List(Node(1,"A", 313, 190)),
                    List(Edge(0,0)))
      
  }

  get("/graph"){

    contentType = formats("json")

    GraphData.all
  }

//  before {
//    contentType = "text/html"
//  }

  get("/") {
    <html>
      <body>

        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.

      </body>
    </html>
  }

  // <script type="application/javascript" src="/js/d3.min.js"></script>
  // submit will hv to post to /graph later on
  get("/draw") {
    <html>

      
      <center>
        
        <canvas id="canvas" width="640" height="360" style="border:3px solid #000000;"></canvas>
        <script type="text/javascript" src="js/script.js"></script>
      </center>

      <button>Submit</button> 
      <button disabled="true">Create Edge</button>

    </html>
  }

// parse matching requests, saving things prefixed with ':' as params
  get("/date/:year/:month/:day") {
    <ul>
      <li>Year: {params("year")}</li>
      <li>Month: {params("month")}</li>
      <li>Day: {params("day")}</li>
    </ul>
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

  // set a session var
  get("/set/:session_val") {
    session("val") = params("session_val")
    <h1>Session var set</h1>
  }

  // see session var
  get("/see") {
    session("val") match {
      case Some(v:String) => v
      case _ => "No session var set"
    }
  }

  // Actions that return byte arrays render a binary response
  // get("/report.pdf") {
  //   contentType = "application/pdf"
  //   val pdf = generatePdf()
  //   pdf.toBytes
  // }

  // enable hello-scalate
  notFound {
    response.setStatus(404)
    "Not found"
  }

}

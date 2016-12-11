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

case class Profile(name: String, girlfriend: String, gik: List[String])

class GraphController extends MyScalatraWebAppStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal

  case class Node(id: Int, title: String, x: Long, y: Long)

                                          // solves the problem of
                                          // rigid class
                                          // options: Map[String, String]
  case class Edge(source: Int, target: Int)
  // case class Edge(attributes: Map[String, String])

  case class Graph(nodes: List[Node], edges: List[Edge], timestmp: Long)

//  println(new Temp hello)


  // val input: String = """{"nodes":[{"id":2,"title":"Node","x":10,"y":10,"data":{"type":"generic"}},{"id":3,"title":"Node","x":15,"y":10,"data":
  //  	{"type":"generic"}},{"id":4,"title":"Node","x":10,"y":15,"data":{"type":"generic"}},{"id":5,"title":"Node","x":15,"y":15,
  //  	"data":{"type":"generic"}},{"id":6,"title":"Node","x":20,"y":20,"data":{"type":"generic"}}],"edges":[{"source":4,"target":3}
  //  	,{"source":3,"target":5},{"source":5,"target":2},{"source":2,"target":4},{"source":4,"target":6}],"weakEdges":[]}"""

  // val trying = new JsonToTikz(input);

  // println(trying.TikzText)

  object GraphData {


    val timestamp: Long = System.currentTimeMillis / 1000

    var all = Graph(
                    List(Node(1,"A", 313, 190)),
                    List(Edge(0,0)),
                    timestamp)
      
  }


  get("/graph"){

    contentType = formats("json")

    GraphData.all
  }

//  before {
//    contentType = "text/html"
//  }

// case class Person(id: Int, name: String)

// post("/create") {
//   parsedBody.extract[Person]
// }

  // val logger =  LoggerFactory.getLogger(getClass)
  
  case class JohnGik(gik: String)

//  post("/john"){
//
//    try {
//
//      def a: List[String] = params.getOrElse("nodes","nodes not found")
//      println(a)
//
////      JohnGik(a)
//
//
//    }
//    catch {
//        case e : Exception => e.printStackTrace()
//    }
//
//  }

  post("/john") {

//    println(Profile.profileFromParams(params))


    try {

      val input = request.body
      println(input)

      val toTikz = new JsonToTikz(input);

      println(toTikz.TikzText)

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

  // <script type="application/javascript" src="/js/d3.min.js"></script>
  // submit will hv to post to /graph later on
  get("/draw") {
    redirect("/js/script.js")
  }

  get("/boww") {
    // return json
    contentType = formats("json")
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
//  get("/set/:session_val") {
//    session("val") = params("session_val")
//    <h1>Session var set</h1>
//  }

  // see session var
  get("/see") {
    session("val") match {
      case Some(v:String) => v
      case _ => "No session var set"
    }
  }

  get("/bossy"){
      // var saveEdges = List[(Int,Int)]
      // var saveWeakEdges = List[(Int,Int)]
      // var newwhat = List[(Int, Int)]
      response.setHeader("Content-Disposition","attachment; filename=bossy.txt");
      "bossy wanna play dota"
      // var blob = new Blob([window.JSON.stringify({"nodes": newwhat, "edges": saveEdges, "weakEdges": saveWeakEdges})], {type: "text/plain;charset=utf-8"});
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

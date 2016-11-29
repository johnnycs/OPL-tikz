package org.scalatra

import org.scalatra._

class ScalatraExample extends ScalatraServlet {

  // send a text/html content type back each time
//  before {
//    contentType = "text/html"
//  }

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
  get("/") {
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

  notFound {
    response.setStatus(404)
    "Not found"
  }
}
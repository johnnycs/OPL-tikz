package com.example.app

import org.scalatra._

class MyScalatraServlet extends MyScalatraWebAppStack {

  get("/") {
    <html>
      <body>

        <canvas id="canvas" width="640" height="360"></canvas>

        
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.



      </body>
    </html>
  }

}

// import com.example.app._
// import org.scalatra._
// import javax.servlet.ServletContext

// class ScalatraBootstrap extends LifeCycle {
//   override def init(context: ServletContext) {
//     context.mount(new MyScalatraServlet, "/*")
//   }
// }

// import com.example.app._
// import org.scalatra._
// import javax.servlet.ServletContext

// class ScalatraBootstrap extends LifeCycle {
//   override def init(context: ServletContext) {
//     context.mount(new FlowersController, "/*")
//   }
// }

import com.example.app._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new ScalatraExample, "/*")
  }
}
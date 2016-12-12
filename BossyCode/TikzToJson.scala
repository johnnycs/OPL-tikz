import scala.util.parsing.json._
import java.io._
class TikzToJson(val line: String){
	val lines = line
	val endIndex = lines.length
	val slice = lines.slice(lines.indexOfSlice("%")+1, endIndex)
}

// object SecondTry{
// 	def main(args: Array[String]) {
// 		val input: String = "ekfpew;k\%{"nodes":[{"id":2,"title":"Node","x":10,"y":10,"data":{"type":"generic"}},{"id":3,"title":"Node","x":15,"y":10,"data":
// 							{"type":"generic"}},{"id":4,"title":"Node","x":10,"y":15,"data":{"type":"generic"}},{"id":5,"title":"Node","x":15,"y":15,
// 							"data":{"type":"generic"}},{"id":6,"title":"Node","x":20,"y":20,"data":{"type":"generic"}}],"edges":[{"source":4,"target":3}
// 							,{"source":3,"target":5},{"source":5,"target":2},{"source":2,"target":4},{"source":4,"target":6}],"weakEdges":[]}"
//       	val trying2 = new TikzToJson(input);
//       	println(trying2.slice)

//    }
// }
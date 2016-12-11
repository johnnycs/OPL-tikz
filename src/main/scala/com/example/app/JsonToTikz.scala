import scala.util.parsing.json._
import java.io._
	
class CC[T] { def unapply(a:Any):Option[T] = Some(a.asInstanceOf[T]) }

object MapNode extends CC[Map[String, Any]]
object node extends CC[List[Any]]
object title extends CC[String]
object source extends CC[Double]
object target extends CC[Double]
object id extends CC[Double]
object x extends CC[Double]
object y extends CC[Double]


//Example: 
//val input: String = "Jason as String"
//val trying = new JsonToTikz(input);
//println(trying.TikzText)        //This is to get final string output, bottom of the code is real example

class JsonToTikz (val line: String) {
	//line is String of json as input
	var lines = line

	//for template
	val header: String = "\\documentclass{article}\n\t\\usepackage{tikz}\n\t\t\\begin{document}\n\t\t\t\\begin{tikzpicture}"
	val footer: String = "\n\t\t\t\\end{tikzpicture}\n\t\t\\end{document}"

	// final output
	var TikzText = header+writeNode()+writeEdges()+footer

	// PARSE DATA JSON

	// json string -> List of Node (List[(String, Int, Int, Int)])
	// each Node have titles, NodeId , Node position x, Node position y
	def parseStringJsonNode(): List[(String, Int, Double, Double)] = {
		val allNode = for {
		    Some(MapNode(map)) <- List(JSON.parseFull(lines))
		    node(nodes) = map("nodes")
		    MapNode(node) <- nodes
		    title(titles) = node("title")
		    id(ids) = node("id")
		    x(xs) = node("x")
		    y(ys) = node("y")
		} yield {
		    (titles, ids.toInt, (xs/20)%.2f, (ys/20)%.2f)
		}
		allNode
	}
 
 	// json string -> List of Edge (List[(Int,Int)])
 	// each Edge have source and destination
	def parseStringJsonEdge(): List[(Int, Int)] = {
		val allEdges = for {
		    Some(MapNode(map)) <- List(JSON.parseFull(lines))
		    node(nodes) = map("edges")
		    MapNode(node) <- nodes
		    source(sources) = node("source")
		    target(targets) = node("target")
		} yield {
		    (sources.toInt,targets.toInt)
		}
		allEdges
	}

	// Write nodes and edge in latex string from parseStringJsonNode function & parseStringJsonEdge
	def writeNode(): String = {
		var output: String = ""
		for( a <- parseStringJsonNode()){
			var gettitle = a._1
			var getid = a._2
			var getx = a._3
			var gety = a._4
			output = output + f"\n\t\t\t\\node at ($getx%.1f,$gety%.1f) [circle,draw] ($getid) {$gettitle};"
		}
		output
	}

	def writeEdges(): String = {
		var output: String = ""
		for( b <- parseStringJsonEdge()){
			var getsource = b._1
			var gettarget = b._2
			output = output + f"\n\t\t\t\\draw[-] ($getsource) to[] ($gettarget);"
		}
		output
	}
}
// object FirstTry{
// 	def main(args: Array[String]) {
// 		val input: String = """{"nodes":[{"id":2,"title":"Node","x":10,"y":10,"data":{"type":"generic"}},{"id":3,"title":"Node","x":15,"y":10,"data":
// 	{"type":"generic"}},{"id":4,"title":"Node","x":10,"y":15,"data":{"type":"generic"}},{"id":5,"title":"Node","x":15,"y":15,
// 	"data":{"type":"generic"}},{"id":6,"title":"Node","x":20,"y":20,"data":{"type":"generic"}}],"edges":[{"source":4,"target":3}
// 	,{"source":3,"target":5},{"source":5,"target":2},{"source":2,"target":4},{"source":4,"target":6}],"weakEdges":[]}"""
//       	val trying = new JsonToTikz(input);
//       	println(trying.TikzText)

//    }
// }
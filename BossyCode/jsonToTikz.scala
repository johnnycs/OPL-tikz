object jsonToTikz extends App {
	import scala.util.parsing.json._
	import java.io._
	val lines = scala.io.Source.fromFile("mydag.json").mkString
	class CC[T] { def unapply(a:Any):Option[T] = Some(a.asInstanceOf[T]) }

	object MapNode extends CC[Map[String, Any]]
	object node extends CC[List[Any]]
	object title extends CC[String]
	object source extends CC[Double]




	object target extends CC[Double]
	object id extends CC[Double]
	object x extends CC[Double]
	object y extends CC[Double]

	val writer = new PrintWriter(new File("output.txt"))
	val header: String = "\\documentclass{article}\n\t\\usepackage{tikz}\n\t\t\\begin{document}\n\t\t\t\\begin{tikzpicture}"
	var middle: String = ""
	val footer: String = "\n\t\t\t\\end{tikzpicture}\n\t\t\\end{document}"

	// parse json data
	val allNode = for {
	    Some(MapNode(map)) <- List(JSON.parseFull(lines))
	    node(nodes) = map("nodes")
	    MapNode(node) <- nodes
	    title(titles) = node("title")
	    id(ids) = node("id")
	    x(xs) = node("x")
	    y(ys) = node("y")
	} yield {
	    (titles, ids.toInt, xs.toInt, ys.toInt)
	}

	val allEdges = for {
	    Some(MapNode(map)) <- List(JSON.parseFull(lines))
	    node(nodes) = map("edges")
	    MapNode(node) <- nodes
	    source(sources) = node("source")
	    target(targets) = node("target")
	} yield {
	    (sources.toInt,targets.toInt)
	}
	// println(allEdges)

	// Write all nodes from json to valible
	for( a <- allNode){
		var gettitle = a._1
		var getid = a._2
		var getx = a._3
		var gety = a._4
		middle = middle + f"\n\t\t\t\\node at ($getx,$gety) [circle,draw] ($getid) {$gettitle};"
	}

	// Write all edges from json to valible
	for( b <- allEdges){
		var getsource = b._1
		var gettarget = b._2
		middle = middle + f"\n\t\t\t\\draw[-] ($getsource) to[] ($gettarget);"
	}

	//write to file
    writer.write(header+middle+footer+f"%$lines")
    writer.close()
}
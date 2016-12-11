object TikzToJson extends App {
	import scala.util.parsing.json._
	import java.io._
	val lines = scala.io.Source.fromFile("output.txt").mkString
	val writer = new PrintWriter(new File("output2.txt"))

	val endIndex = lines.length
	val index = lines.indexOfSlice("%")

	val slice = lines.slice(index+1, endIndex)

	writer.write(slice)
    writer.close()
}
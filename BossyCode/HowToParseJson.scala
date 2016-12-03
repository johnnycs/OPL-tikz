object HowToParseJson extends App {
	import scala.util.parsing.json._
	class CC[T] { def unapply(a:Any):Option[T] = Some(a.asInstanceOf[T]) }

	object M extends CC[Map[String, Any]]
	object L extends CC[List[Any]]
	object S extends CC[String]
	object D extends CC[Double]
	object B extends CC[Boolean]

	val jsonString =
	    """
	      |{
	      | "languages": [{
	      |     "name": "English",
	      |     "is_active": true,
	      |     "completeness": 2.5
	      | }, {
	      |     "name": "Latin",
	      |     "is_active": false,
	      |     "completeness": 0.9
	      | }]
	      |}
	    """.stripMargin

	val result = for {
	    Some(M(map)) <- List(JSON.parseFull(jsonString))
	    L(languages) = map("languages")
	    M(language) <- languages
	    S(name) = language("name")
	    B(active) = language("is_active")
	    D(completeness) = language("completeness")
	} yield {
	    (name, active, completeness)
	}

	assert( result == List(("English",true,2.5), ("Latin",false,0.9)))
}
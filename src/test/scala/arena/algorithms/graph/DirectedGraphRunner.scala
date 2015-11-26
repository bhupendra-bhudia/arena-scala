package arena.algorithms.graph

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         26/11/2015 15:36
 */
object DirectedGraphRunner {

  def main(args: Array[String]) {
    var graph = new DirectedGraph[String, Int]()

    graph.addEdge("A", "B", 5)
    graph.addEdge("A", "C", 6)
    graph.addEdge("B", "E", 2)
    graph.addEdge("C", "D", 10)
    graph.addEdge("D", "E", 2)

    graph.show()

    println
    println

    graph.foreach { from =>
      graph.edgesFrom(from).foreach { to => println("(" + from + " ~~[" + graph.weightOf(from, to).get + "]~~> " + to + ")") }

    }
  }
}

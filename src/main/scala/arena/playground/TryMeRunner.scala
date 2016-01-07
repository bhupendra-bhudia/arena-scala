package arena.playground

import scala.pickling._
import json._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         07/01/2016.
 */
object TryMeRunner {

  def main(args: Array[String]) {

    // Pickle to a JSON string
    val pckl = List(1, 2, 3, 4).pickle
    println(f"JSON Pickle: '$pckl%s' completed")

    // Unpickling is just as easy:
    val lst = pckl.unpickle[List[Int]]
    println(f"JSON Unpickle: '$lst%s' completed")
  }
}

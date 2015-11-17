/**
 * Created by bbhudia on 01/10/2015.
 */

package arena.algorithms

import scala.annotation.tailrec

object MathsAlgorithms {
  // Standard recursive factorial using match
  def factorial(n: Int): Int = n match {
    case 0 => 1
    case 1 => 1
    case _ => n * factorial(n - 1)
  }

  // Tail-recursive factorial using accumulator
  def factorialAcc(n: Int): Int = {
    @tailrec
    def factorialAccumulator(acc: Int, n: Int): Int = {
      if (n <= 1) acc
      else factorialAccumulator(n * acc, n - 1)
    }
    factorialAccumulator(1, n)
  }


  def main(args: Array[String]) {
    var a = 0
    for (a <- 1 to 5) {
      val f = factorial(a)
      println(f"Factorial of '$a%d' is $f%d")
    }
    println()

    for (a <- 1 to 5) {
      val f = factorialAcc(a)
      println(f"Factorial of '$a%d' is $f%d")
    }
    println()
  }
}
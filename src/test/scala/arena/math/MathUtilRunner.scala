package arena.math

import arena.math.MathUtil._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         25/11/2015 08:57
 */
object MathUtilRunner {
  def main(args: Array[String]) {
    var i = 0

    for (i <- 0 until 10) {
      val f = factorial(i)
      println(f"Vanilla Factorial of '$i%d' is $f%d")
      val f2 = factorialBig(i)
      println(f"BigIntr Factorial of '$i%d' is $f2%d")
      val f3 = factorialAcc(i)
      println(f"Accumul Factorial of '$i%d' is $f3%d")
      println()
    }
    println()
  }
}

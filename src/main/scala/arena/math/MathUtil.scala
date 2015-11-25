/**
 * Created by bbhudia on 01/10/2015.
 */

package arena.math

import scala.annotation.tailrec

object MathUtil {
  // Standard recursive factorial using match
  def factorial(n: Int): Int = n match {
    case 0 => 1
    case 1 => 1
    case _ => n * factorial(n - 1)
  }

  def factorialBig(x: BigInt): BigInt =
    if (x <= 1) 1 else x * factorialBig(x - 1)

  // Tail-recursive factorial using accumulator
  def factorialAcc(n: Int): Int = {
    @tailrec
    def factorialAccumulator(acc: Int, n: Int): Int = {
      if (n <= 1) acc else factorialAccumulator(n * acc, n - 1)
    }
    factorialAccumulator(1, n)
  }
}
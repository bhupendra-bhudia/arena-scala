package arena.math

import arena.math.MathUtil._
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         25/11/2015 08:57
 */
class MathUtilTestSpec extends FlatSpec with Matchers {

  val expected = Array(1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880)
  var i = 0

  // Regular factorial
  ("Vanilla Factorial") should "match expected results" in {
    for (i <- 0 until 10) {
      val f = factorial(i)
      f should be(expected(i))
    }
  }

  // BigInt factorial
  ("BigInt Factorial") should "match expected results" in {
    for (i <- 0 until 10) {
      val f = factorialBig(i)
      f should be(expected(i))
    }
  }

  // Accumulating factorial
  ("Accumulating Factorial") should "match expected results" in {
    for (i <- 0 until 10) {
      val f = factorialAcc(i)
      f should be(expected(i))
    }
  }
}

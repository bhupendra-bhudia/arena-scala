package arena.math

import arena.math.MathUtil._
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         25/11/2015 08:57
 */
class MathUtilTestSpec extends FlatSpec with Matchers {

  val expected = Array(1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880)
  val args = 0 until 10

  // Regular factorial
  ("Vanilla Factorial") should "match expected results" in {
    args.foreach(a => factorial(a) should be(expected(a)))
  }

  // BigInt factorial
  ("BigInt Factorial") should "match expected results" in {
    args.foreach(a => factorialBig(a) should be(expected(a)))
  }

  // Accumulating factorial
  ("Accumulating Factorial") should "match expected results" in {
    args.foreach(a => factorialAcc(a) should be(expected(a)))
  }
}

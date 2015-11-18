package arena.analytics

import arena.analytics.OptionsPricer._
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 16:55
 */
class OptionsPricerTestSpec extends FlatSpec with Matchers {

  val S0 = 100.0
  val K = 110.0
  val r = 0.03
  val sigma = 0.1
  val T = 0.5

  // Black-Scholes Model
  ("A Black-Scholes Model") should "price European Call option" in {
    val price = black_scholes_european_pricer(S0, K, r, sigma, T, true)
    price should be equals (0.4702029691054719)
  }
  it should "price European Put option" in {
    val price = black_scholes_european_pricer(S0, K, r, sigma, T, false)
    price should be equals (8.832516325442384)
  }

  // Binomial Tree Model
  ("A Binomial Tree Model") should "price European Call option" in {
    val price = binomial_tree_european_pricer(S0, K, r, sigma, T, true)
    price should equal(0.4692598461147759)
  }
  it should "price European Put option" in {
    val price = binomial_tree_european_pricer(S0, K, r, sigma, T, false)
    price should be equals (8.831573202451107)
  }

  // Monte Carlo Model
  ("A Monte Carlo Model") should "price European Call option" in {
    val price = monte_carlo_european_pricer(S0, K, r, sigma, T, true)
    price should be equals (0.48392700175248)
  }
  it should "price European Put option" in {
    val price = monte_carlo_european_pricer(S0, K, r, sigma, T, false)
    price should be equals (8.86694065078978)
  }
}

package arena.analytics

import arena.analytics.OptionsPricer._
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 16:55
 */
class OptionsPricerTestSpec extends FlatSpec with Matchers {

  val tolerance = 0.000002
  val weakTolerance = 0.2
  val underlyingPrice = 100.0
  val strikePrice = 110.0
  val crr = 0.03
  val volatility = 0.1
  val timeToMaturity = 0.5

  // Black-Scholes Model
  ("A Black-Scholes Model") should "price European Call option" in {
    val optionPrice = black_scholes_european_pricer(underlyingPrice, strikePrice, crr, volatility, timeToMaturity, true)
    optionPrice should be(0.4702029691054719 +- tolerance)
  }
  it should "price European Put option" in {
    val optionPrice = black_scholes_european_pricer(underlyingPrice, strikePrice, crr, volatility, timeToMaturity, false)
    optionPrice should be(8.832516325442384 +- tolerance)
  }

  // Binomial Tree Model
  ("A Binomial Tree Model") should "price European Call option" in {
    val optionPrice = binomial_tree_european_pricer(underlyingPrice, strikePrice, crr, volatility, timeToMaturity, true)
    optionPrice should be(0.4692598461147759 +- tolerance)
  }
  it should "price European Put option" in {
    val optionPrice = binomial_tree_european_pricer(underlyingPrice, strikePrice, crr, volatility, timeToMaturity, false)
    optionPrice should be(8.831573202451107 +- tolerance)
  }

  // Monte Carlo Model
  ("A Monte Carlo Model") should "price European Call option" in {
    val optionPrice = monte_carlo_european_pricer(underlyingPrice, strikePrice, crr, volatility, timeToMaturity, true)
    optionPrice should be(0.48392700175248 +- weakTolerance)
  }
  it should "price European Put option" in {
    val optionPrice = monte_carlo_european_pricer(underlyingPrice, strikePrice, crr, volatility, timeToMaturity, false)
    optionPrice should be(8.86694065078978 +- weakTolerance)
  }
}

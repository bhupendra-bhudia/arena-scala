package arena.analytics.option

import OptionsPricer._
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         18/11/2015 16:55
 */
class OptionsPricerTestSpec extends FlatSpec with Matchers {

  val tolerance = 0.000002
  val weakTolerance = 0.2
  val underlyingPrice = 100.0
  val strikePrice = 110.0
  val interestRate = 0.03
  val volatility = 0.1
  val timeToMaturity = 0.5

  // Black-Scholes Model
  ("A Black-Scholes Model") should "price European Call option" in {
    val optionPrice = black_scholes_european_pricer(underlyingPrice, strikePrice, interestRate, volatility, timeToMaturity, true)
    optionPrice should be(0.4702029691054719 +- tolerance)
  }
  it should "price European Put option" in {
    val optionPrice = black_scholes_european_pricer(underlyingPrice, strikePrice, interestRate, volatility, timeToMaturity, false)
    optionPrice should be(8.832516325442384 +- tolerance)
  }

  // Binomial Tree Model
  ("A Binomial Tree Model") should "price European Call option" in {
    val optionPrice = binomial_tree_european_pricer(underlyingPrice, strikePrice, interestRate, volatility, timeToMaturity, true)
    optionPrice should be(0.4692598461147759 +- tolerance)
  }
  it should "price European Put option" in {
    val optionPrice = binomial_tree_european_pricer(underlyingPrice, strikePrice, interestRate, volatility, timeToMaturity, false)
    optionPrice should be(8.831573202451107 +- tolerance)
  }

  // Monte Carlo Model
  ("A Monte Carlo Model") should "price European Call option" in {
    val optionPrice = monte_carlo_european_pricer(underlyingPrice, strikePrice, interestRate, volatility, timeToMaturity, true)
    optionPrice should be(0.48392700175248 +- weakTolerance)
  }
  it should "price European Put option" in {
    val optionPrice = monte_carlo_european_pricer(underlyingPrice, strikePrice, interestRate, volatility, timeToMaturity, false)
    optionPrice should be(8.86694065078978 +- weakTolerance)
  }
}

object OptionsPricerRunner {
  def main(args: Array[String]) {

    val S0 = 100.0
    val K = 110.0
    val r = 0.03
    val sigma = 0.1
    val T = 0.5

    println()
    println("S0\tstock price at time 0: " + S0)
    println("K\tstrike price: " + K)
    println("r\tcontinuously compounded risk-free rate: " + r)
    println("sigma\tvolatility of the stock price per year: " + sigma)
    println("T\ttime to maturity in trading years: " + T)

    println()
    val c_BS = black_scholes_european_pricer(S0, K, r, sigma, T, true)
    println("c_BS\tBlack-Scholes European call price: " + c_BS)
    val p_BS = black_scholes_european_pricer(S0, K, r, sigma, T, false)
    println("p_BS\tBlack-Scholes European put price: " + p_BS)

    println()
    val c_BT = binomial_tree_european_pricer(S0, K, r, sigma, T, true)
    println("c_BT\tBinomial tree European call price: " + c_BT)
    val p_BT = binomial_tree_european_pricer(S0, K, r, sigma, T, false)
    println("p_BT\tBinomial tree European put price: " + p_BT)

    println()
    val c_MC = monte_carlo_european_pricer(S0, K, r, sigma, T, true)
    println("c_BT\tMonte Carlo European call price: " + c_MC)
    val p_MC = monte_carlo_european_pricer(S0, K, r, sigma, T, false)
    println("p_BT\tMonte Carlo European put price: " + p_MC)
  }
}
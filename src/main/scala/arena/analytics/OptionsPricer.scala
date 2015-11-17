/**
 * Created by bbhudia on 05/11/2015.
 */

package arena.analytics

import breeze.stats.distributions._

object OptionsPricer {

  val norm = new Gaussian(0, 1);

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

  def black_scholes_european_pricer(S0: Double, K: Double, r: Double, sigma: Double, T: Double, is_call: Boolean = true): Double = {
    if (is_call) {
      S0 * norm.cdf(d1(S0, K, r, sigma, T)) - K * math.exp(-r * T) * norm.cdf(d2(S0, K, r, sigma, T))
    } else {
      K * math.exp(-r * T) * norm.cdf(-d2(S0, K, r, sigma, T)) - S0 * norm.cdf(-d1(S0, K, r, sigma, T))
    }
  }

  def d1(S0: Double, K: Double, r: Double, sigma: Double, T: Double): Double = {
    (math.log(S0 / K) + (r + math.pow(sigma, 2) / 2) * T) / (sigma * math.sqrt(T))
  }

  def d2(S0: Double, K: Double, r: Double, sigma: Double, T: Double): Double = {
    (math.log(S0 / K) + (r - math.pow(sigma, 2) / 2) * T) / (sigma * math.sqrt(T))
  }

  def binomial_tree_european_pricer(S0: Double, K: Double, r: Double, sigma: Double, T: Double, is_call: Boolean = true, N: Int = 100): Double = {
    val deltaT = T / N
    val u = math.exp(sigma * math.sqrt(deltaT))
    val d = 1.0 / u

    val a = math.exp(r * deltaT)
    val p = (a - d) / (u - d)
    val oneMinusP = 1.0 - p
    val payoff = make_payoff(is_call, K)

    // Initialise our fs(i)(j) tree with zeros
    // fs  = [[0], [0,0], ..., [0,0,...,0]]
    //  i  =   0,   1, ...,     N
    // len =   1,   2, ...,     N+1
    val fs: Array[Array[Double]] = Array.ofDim(N + 1)
    for (i <- 0 to N) {
      fs(i) = Array.ofDim(i + 1)
    }

    // Compute the payoffs at the leaves fs(N)(j)
    for (j <- 0 to N) {
      fs(N)(j) = payoff(S0 * math.pow(u, j) * math.pow(d, (N - j)))
    }

    // Backwards-propagate discounted price for each internal node, starting from the payoff leaves
    for (i <- N - 1 until -1 by -1) {
      for (j <- 0 to i) {
        fs(i)(j) = math.exp(-r * deltaT) * (p * fs(i + 1)(j + 1) + oneMinusP * fs(i + 1)(j))
      }
    }

    // Discounted option price at root node
    fs(0)(0)
  }

  def make_payoff(is_call: Boolean, k: Double) =
    if (is_call) {
      (s: Double) => {
        math.max(s - k, 0.0)
      }
    } else {
      (s: Double) => {
        math.max(k - s, 0.0)
      }
    }

  def monte_carlo_european_pricer(S0: Double, K: Double, r: Double, sigma: Double, T: Double, is_call: Boolean = true, N: Int = 100, num_paths: Int = 5000): Double = {
    val deltaT = T / N
    val sqrtDt = math.sqrt(deltaT)
    val payoff = make_payoff(is_call, K)

    val fs: Array[Double] = Array.ofDim(num_paths)
    for (i <- 0 until num_paths) {
      var S = S0
      val epsilons = norm.sample(N)
      for (j <- 0 until N) {
        S += r * S * deltaT + sigma * S * epsilons(j) * sqrtDt
      }
      fs(i) = payoff(S)
    }
    val mv = breeze.stats.meanAndVariance(fs)
    mv.mean
  }
}


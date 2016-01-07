/**
 * Created by bbhudia on 05/11/2015.
 */

package arena.analytics.option

import breeze.stats.distributions._

object OptionsPricer {

  val norm = new Gaussian(0, 1);

  def black_scholes_european_pricer(S0: Double, K: Double, r: Double, sigma: Double, T: Double, is_call: Boolean = true): Double = {
    val d1val = d1(S0, K, r, sigma, T)
    val d2val = d2(S0, K, r, sigma, T)
    if (is_call) {
      S0 * norm.cdf(d1val) - K * math.exp(-r * T) * norm.cdf(d2val)
    } else {
      K * math.exp(-r * T) * norm.cdf(-d2val) - S0 * norm.cdf(-d1val)
    }
  }

  def d1(S0: Double, K: Double, r: Double, sigma: Double, T: Double): Double = {
    (math.log(S0 / K) + (r + math.pow(sigma, 2) / 2) * T) / (sigma * math.sqrt(T))
  }

  def d2(S0: Double, K: Double, r: Double, sigma: Double, T: Double): Double = {
    (math.log(S0 / K) + (r - math.pow(sigma, 2) / 2) * T) / (sigma * math.sqrt(T))
  }

  def delta(S0: Double, K: Double, r: Double, sigma: Double, T: Double, is_call: Boolean = true): Double = {
    if (sigma == 0 || T == 0) {
      if (is_call) {
        if (S0 > K) 1 else 0
      } else {
        if (S0 < K) -1 else 0
      }
    } else {
      if (is_call) {
        norm.cdf(d1(S0, K, r, sigma, T)) - math.exp(-r * T)
      } else {
        -norm.cdf(d1(S0, K, r, sigma, T)) - math.exp(-r * T)
      }
    }
  }

  def vega(S0: Double, K: Double, r: Double, sigma: Double, T: Double, is_call: Boolean = true): Double = {
    if (sigma == 0 || T == 0) {
      0
    } else {
      S0 * math.exp(-r * T) * norm.pdf(d1(S0, K, r, sigma, T)) * math.pow(T, 0.5)
    }
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


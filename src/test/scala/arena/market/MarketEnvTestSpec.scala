package arena.market

import arena.foundation.Currency
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         18/11/2015 16:47
 */
class MarketEnvTestSpec extends FlatSpec with Matchers {

  val tolerance = 0.0002

  val gbp = Currency.GBP
  val usd = Currency.USD
  val eur = Currency.EUR
  val jpy = Currency.JPY

  val rates = FxRate(gbp, usd, 1.57) :: FxRate(eur, usd, 1.31) :: Nil
  val env = SimpleEnv((rates map (r => r.pair -> r)).toMap)

  // Defined rates
  ("Valid FX Rates") should "EUR to GBP is defined" in {
    val fxpair: Option[FxRate] = env.rate(eur)(gbp)
    fxpair.get.rate.toDouble should be(0.83439 +- tolerance)
  }
  it should "GBP to EUR is defined via triangulation" in {
    val fxpair: Option[FxRate] = env.rate(gbp)(eur)
    fxpair.get.rate.toDouble should be(1.19847 +- tolerance)
  }
  it should "GBP to USD is defined" in {
    val fxpair: Option[FxRate] = env.rate(gbp)(usd)
    fxpair.get.rate.toDouble should be(1.57 +- tolerance)
  }
  it should "GBP to JPY is NOT defined" in {
    val fxpair: Option[FxRate] = env.rate(gbp)(jpy)
    fxpair should be(None)
  }
}

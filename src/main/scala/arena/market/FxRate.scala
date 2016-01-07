package arena.market

import arena.foundation.Currency._

//type CcyPair = (Currency, Currency)

case class FxRate(from: Currency, to: Currency, rate: BigDecimal) {
  lazy val pair: (Currency, Currency) = from -> to

  def unary_~ = FxRate(to, from, 1 / rate)

  def *(that: FxRate) = {
    require(this.to == that.from)
    FxRate(this.from, that.to, this.rate * that.rate)
  }
}


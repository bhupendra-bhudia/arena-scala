package arena.types.market

import arena.types.foundation.Currency
import arena.types.foundation.Currency._

/**
 * Created by bbhudia on 23/11/2015.
 */
trait MarketEnv {
  def rate: Currency => Currency => Option[FxRate]
}

case class SimpleEnv(rates: Map[(Currency, Currency), FxRate])
  extends MarketEnv {

  def rate = from => to => {
    def attempt(c1: Currency, c2: Currency)
    = rates get (c1 -> c2) orElse (rates get (c2 -> c1) map (~_))

    def viaUsd = {
      val usd = Currency.USD
      for {
        f2u <- attempt(from, usd)
        u2t <- attempt(usd, to)
      } yield f2u * u2t
    }

    attempt(from, to) orElse viaUsd
  }
}

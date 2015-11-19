package arena.types.transaction

import java.time.LocalDate

import arena.types.foundation.BuySell._
import arena.types.foundation.Currency._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 16:50
 */
case class Trade(id: Int, buySell: BuySell, currency: Currency, amount: Double, tradeDate: LocalDate, settlementDate: LocalDate) {
}

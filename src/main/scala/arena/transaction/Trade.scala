package arena.transaction

import java.time.LocalDate

import arena.foundation.BuySell._
import arena.foundation.Currency._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         18/11/2015 16:50
 */
case class Trade(id: Int, buySell: BuySell, currency: Currency, amount: Double, tradeDate: LocalDate, settlementDate: LocalDate) {
}

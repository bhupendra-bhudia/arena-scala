package arena.types.foundation

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 16:35
 */
object BuySell extends Enum[BuySell]
sealed trait BuySell extends BuySell.Value

case object BUY extends BuySell
case object SELL extends BuySell


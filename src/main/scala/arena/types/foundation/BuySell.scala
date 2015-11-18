package arena.types.foundation

/**
 * Created by bbhudia on 18/11/2015.
 */
object BuySell extends Enum[BuySell]
sealed trait BuySell extends BuySell.Value

case object BUY extends BuySell
case object SELL extends BuySell


package arena.foundation

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         18/11/2015 16:35
 */
object BuySell extends Enumeration {
  type BuySell = Value
  val BUY = BuySellVal(1)
  val SELL = BuySellVal(-1)

  protected case class BuySellVal(sign: Int) extends super.Val()

  implicit def convert(value: Value) = value.asInstanceOf[BuySellVal]
}


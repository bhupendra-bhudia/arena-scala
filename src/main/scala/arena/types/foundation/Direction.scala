package arena.types.foundation

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 13:35
 */
object Direction extends Enum[Direction]
sealed trait Direction extends Direction.Value

case object TOP extends Direction
case object RIGHT extends Direction
case object BOTTOM extends Direction
case object LEFT extends Direction



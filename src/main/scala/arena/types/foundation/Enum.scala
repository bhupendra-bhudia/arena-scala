package arena.types.foundation

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 15:35
 */
trait Enum[A] {
  trait Value {
    self: A =>
    _values :+= this
  }

  private var _values = List.empty[A]

  def values = _values
}
package arena.types.foundation

/**
 * Created by bbhudia on 18/11/2015.
 */
trait Enum[A] {
  trait Value {
    self: A =>
    _values :+= this
  }

  private var _values = List.empty[A]

  def values = _values
}
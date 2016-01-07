package arena.foundation

import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         19/11/2015 09:34
 */
class DirectionTestSpec extends FlatSpec with Matchers {

  val top = TOP
  val bottom = BOTTOM
  val left = LEFT
  val right = RIGHT
  val enumValues = Direction.values

  // TOP Enum check
  ("A Direction TOP") should "match TOP enum" in {
    top should be(TOP)
  }
  it should "have a value of TOP" in {
    top.toString should be("TOP")
  }

  // LEFT Enum check
  ("A Direction LEFT") should "match LEFT enum" in {
    left should be(LEFT)
  }
  it should "have a value of LEFT" in {
    left.toString should be("LEFT")
  }

  // BOTTOM Enum check
  ("A Direction BOTTOM") should "match BOTTOM enum" in {
    bottom should be(BOTTOM)
  }
  it should "have a value of BOTTOM" in {
    bottom.toString should be("BOTTOM")
  }

  // RIGHT Enum check
  ("A Direction RIGHT") should "match RIGHT enum" in {
    right should be(RIGHT)
  }
  it should "have a value of RIGHT" in {
    right.toString should be("RIGHT")
  }

  // Direction check
  ("A Direction Enum") should "contain 4 enums" in {
    enumValues should have size (4)
  }
  it should "have a value of TOP" in {
    enumValues should contain(TOP)
  }
  it should "have a value of RIGHT" in {
    enumValues should contain(RIGHT)
  }
  it should "have a value of BOTTOM" in {
    enumValues should contain(BOTTOM)
  }
  it should "have a value of LEFT" in {
    enumValues should contain(LEFT)
  }

}

package arena.types.foundation

import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 09:49
 */
class BuySellTestSpec extends FlatSpec with Matchers {

  val buy = BUY
  val sell = SELL
  val enumValues = BuySell.values

  // BUY Enum check
  ("A BuySell BUY") should "match BUY enum" in {
    buy should be(BUY)
  }
  it should "have a value of BUY" in {
    buy.toString should be("BUY")
  }

  // SELL Enum check
  ("A BuySell SELL") should "match SELL enum" in {
    sell should be(SELL)
  }
  it should "have a value of SELL" in {
    sell.toString should be("SELL")
  }

  // BuySell check
  ("A BuySell Enum") should "contain two enums" in {
    enumValues should have size (2)
  }
  it should "have a value of BUY" in {
    enumValues should contain(BUY)
  }
  it should "have a value of SELL" in {
    enumValues should contain(SELL)
  }

}

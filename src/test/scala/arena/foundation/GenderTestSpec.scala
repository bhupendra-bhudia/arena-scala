package arena.foundation

import arena.foundation.Gender._
import org.scalatest._

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         18/11/2015 16:49
 */
class GenderTestSpec extends FlatSpec with Matchers {

  val male = MALE
  val female = FEMALE
  val enumValues = Gender.values

  // MALE Enum check
  ("A Gender MALE") should "match MALE enum" in {
    male should be(MALE)
  }
  it should "have a value of MALE" in {
    male.toString should be("MALE")
  }
  it should "from string be MALE enum" in {
    male should be(Gender.withName("MALE"))
  }

  // FEMALE Enum check
  ("A Gender FEMALE") should "match FEMALE enum" in {
    female should be(FEMALE)
  }
  it should "have a value of FEMALE" in {
    female.toString should be("FEMALE")
  }
  it should "from string be FEMALE enum" in {
    female should be(Gender.withName("FEMALE"))
  }

  // Gender check
  ("A Gender Enum") should "contain two enums" in {
    enumValues should have size (2)
  }
  it should "have a value of MALE" in {
    enumValues should contain(MALE)
  }
  it should "have a value of FEMALE" in {
    enumValues should contain(FEMALE)
  }

}

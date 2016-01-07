package arena.collection

import org.scalatest.{FlatSpec, Matchers}

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         27/11/2015 14:42
 */
class TrieTreeTestSpec extends FlatSpec with Matchers {

  var tree = new TrieTree()
  val words: Array[String] = Array("an", "ant", "all", "allot", "alloy", "aloe", "are", "ate", "be")
  var word: String = ""
  for (word <- words) {
    tree.add(word)
  }
  tree.print

  "A TriTree dictionary" should "have these words" in {
    val goodWords: Array[String] = Array("an", "ant", "ANT", "all", "allot", "alloy", "aloe", "are", "ate", "be")
    for (word <- goodWords) {
      tree.contains(word) should be(true)
    }
  }
  it should "not have these words" in {
    val badWords: Array[String] = Array("aloft", "mad", "hat", "sky")
    for (word <- badWords) {
      tree.contains(word) should be(false)
    }
  }

}

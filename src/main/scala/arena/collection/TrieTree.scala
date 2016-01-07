package arena.collection

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         27/11/2015 14:42
 */
object TrieTree {
  private val ASCII_START: Int = 'a'.toInt
}

class TrieTree {

  private case class TrieNode(letter: Char, links: Array[TrieNode], isFullWord: Boolean) {
    def this(letter: Char, isFullWord: Boolean) = {
      this(letter, new Array[TrieNode](26), isFullWord)
    }
  }

  private var root = new TrieNode('\0', false)

  def cleansed(word: String): String = word.toLowerCase.replaceAll("[^a-z]", "")

  def add(inputWord: String) {
    val word: String = cleansed(inputWord)
    val wordlen = word.length
    val letters = word.toCharArray

    var node = root
    var i = 0
    var ti = 0
    for (letter <- letters) {
      ti = letter - TrieTree.ASCII_START

      if (node.links(ti) == null) {
        node.links(ti) = new TrieNode(letter, (i == wordlen - 1))
      }
      node = node.links(ti)
      i += 1
    }
  }

  def contains(inputWord: String): Boolean = {
    val letters = cleansed(inputWord).toCharArray

    var node = root
    var i = 0
    var ti = 0
    for (letter <- letters) {
      ti = letter - TrieTree.ASCII_START

      if (node == null)
        return false

      node = node.links(ti)
      i += 1
    }

    if (node != null && node.isFullWord)
      true
    else
      false
  }

  private def printTree(node: TrieNode, level: Int, branch: Array[Char]) {
    if (node == null)
      return

    // Depth-first traversal to re-construct the word
    var link: TrieNode = null
    for (link <- node.links) {
      branch(level) = node.letter
      printTree(link, level + 1, branch)
    }

    if (node.isFullWord)
      println(branch.subSequence(1, level + 1).toString)
  }

  def print {
    val branch = new Array[Char](50)
    printTree(root, 0, branch)
  }
}


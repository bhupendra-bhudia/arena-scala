package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         16/11/2015 22:44
 */
class BubbleSort extends Sortable {
  def sort(nums: Array[Int]) {
    val size: Int = nums.length
    var i: Int = 0
    var j: Int = 0
    for (i <- 0 until size) {
      for (j <- 1 until size) {
        if (nums(j - 1) > nums(j)) {
          swap(nums, j - 1, j)
        }
      }
    }
  }
}


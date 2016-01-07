package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         16/11/2015 22:44
 */
class SelectionSort extends Sortable {
  def sort(nums: Array[Int]) {
    val size: Int = nums.length
    var i: Int = 0
    var j: Int = 0
    for (i <- 0 until size) {
      var k: Int = i
      for (j <- i + 1 until size) {
        if (nums(j) < nums(k)) {
          k = j
        }
      }
      swap(nums, k, i)
    }
  }
}

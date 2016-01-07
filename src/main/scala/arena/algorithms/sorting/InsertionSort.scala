package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         17/11/2015 12:44
 */
class InsertionSort extends Sortable {
  def sort(nums: Array[Int]) {
    val size: Int = nums.length
    var i: Int = 0
    for (i <- 1 until size) {
      val temp = nums(i)
      var j = i - 1
      while (j >= 0 && temp < nums(j)) {
        nums(j + 1) = nums(j)
        j -= 1
      }
      nums(j + 1) = temp
    }
  }
}


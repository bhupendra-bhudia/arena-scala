package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         16/11/2015 22:44
 */
class QuickSort extends Sortable {
  def sort(nums: Array[Int]) {
    if (nums.length > 1) {
      quickSort(nums, 0, nums.length - 1)
    }
  }

  private def quickSort(nums: Array[Int], lower: Int, upper: Int) {
    var l: Int = lower
    var u: Int = upper
    val pivot: Int = nums(l + (u - l) / 2)
    while (l <= u) {
      while (nums(l) < pivot) {
        l += 1
      }
      while (nums(u) > pivot) {
        u -= 1
      }
      if (l <= u) {
        swap(nums, l, u)
        l += 1
        u -= 1
      }
    }
    if (lower < u) {
      quickSort(nums, lower, u)
    }
    if (l < upper) {
      quickSort(nums, l, upper)
    }
  }
}


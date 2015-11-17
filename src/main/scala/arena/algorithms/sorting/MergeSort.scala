package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         17/11/2015 12:44
 */
class MergeSort extends Sortable {
  def sort(nums: Array[Int]) {
    val workspace = new Array[Int](nums.length)
    mergeSort(nums, workspace, 0, nums.length - 1)
  }

  private def mergeSort(nums: Array[Int], workspace: Array[Int], left: Int, right: Int) {
    if (left < right) {
      val center: Int = (left + right) / 2
      mergeSort(nums, workspace, left, center)
      mergeSort(nums, workspace, center + 1, right)
      merge(nums, workspace, left, center + 1, right)
    }
  }

  private def merge(nums: Array[Int], workspace: Array[Int], leftPos: Int, rightPos: Int, rightEnd: Int) {
    var l: Int = leftPos
    var r: Int = rightPos
    val leftEnd = rightPos - 1
    val numElements = rightEnd - leftPos + 1

    var tmpPos = leftPos
    while (l <= leftEnd && r <= rightEnd) {
      if (nums(l) <= nums(r)) {
        workspace(tmpPos) = nums(l)
        l += 1
      }
      else {
        workspace(tmpPos) = nums(r)
        r += 1
      }
      tmpPos += 1
    }

    // Copy rest of left half
    while (l <= leftEnd) {
      workspace(tmpPos) = nums(l);
      tmpPos += 1
      l += 1
    }

    // Copy rest of right half
    while (r <= rightEnd) {
      workspace(tmpPos) = nums(r)
      tmpPos += 1
      r += 1
    }

    // Copy workspace back
    var i: Int = 0
    var j: Int = rightEnd
    for (i <- 0 until numElements) {
      nums(j) = workspace(j)
      j -= 1
    }
  }
}

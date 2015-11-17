package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         17/11/2015 14:44
 */
class HeapSort extends Sortable {
  def maxHeap(a: Array[Int], i: Int, size: Int): Unit = {
    val l = 2 * i + 1
    val r = l + 1
    var m = -1

    if (l < size && a(l) > a(i)) {
      m = l
    } else {
      m = i
    }

    if (r < size && a(r) > a(m)) {
      m = r
    } else {
      m = m
    }

    if (m != i) {
      swap(a, i, m)
      maxHeap(a, m, size)
    }
  }

  def buildMaxHeap(a: Array[Int], size: Int): Unit = {
    val hs = size / 2
    for (i <- 0 to hs) {
      maxHeap(a, hs - i, size)
    }
  }


  def sort(nums: Array[Int]) {
    val size = nums.length
    buildMaxHeap(nums, size)
    for (i <- (0 until size).reverse) {
      swap(nums, 0, i)
      maxHeap(nums, 0, i)
    }
  }
}


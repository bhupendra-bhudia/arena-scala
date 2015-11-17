package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         16/11/2015 22:39
 */
trait Sortable {
  def sort(nums: Array[Int])

  protected def swap(nums: Array[Int], i: Int, j: Int) {
    val tmp: Int = nums(i)
    nums(i) = nums(j)
    nums(j) = tmp
  }
}

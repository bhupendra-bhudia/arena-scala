package arena.algorithms.sorting

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         16/11/2015 22:44
 */
class FunctionalSortingAlgorithms {
  // Functional-styled quicksort
  def quicksort(xs: Array[Int]): Array[Int] = {
    if (xs.length <= 1) {
      xs
    } else {
      val pivot = xs(xs.length / 2)

      Array.concat(
        quicksort(xs filter (pivot >)),
        xs filter (pivot ==),
        quicksort(xs filter (pivot <))
      )
    }
  }


  def main(args: Array[String]) {
    val sample1 = Array(9, 5, 1, 3, 8, 2, 1, 5, 10, -20)

    val result1 = quicksort(sample1)
    println("Quicksorted: " + result1.mkString("[", ",", "]"))
  }
}
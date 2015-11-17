package arena.algorithms.sorting

import scala.util.Random

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         16/11/2015 22:49
 */
class SortingTestPad(sampleSize: Int) {

  def run(): Unit = {
    runCase(Array[Sortable] (
    new BubbleSort,
    new HeapSort,
    new InsertionSort,
    new MergeSort,
    new QuickSort,
    new SelectionSort
    ))
  }

  def runCase(sorters: Array[Sortable]): Unit = {
    val nums: Array[Int] = Array.fill(sampleSize)(Random.nextInt(100))

    val expected = nums.clone()
    scala.util.Sorting.quickSort(expected)

    for (sorter <- sorters) {
      val n = nums.clone()

      println
      println(sorter.getClass.getSimpleName)
      println("Before:   " + n.mkString("[", ", ", "]"))
      val start: Long = System.currentTimeMillis

      sorter.sort(n)

      val elapsedTimeMillis: Long = System.currentTimeMillis - start
      println("After:    " + n.mkString("[", ", ", "]"))
      println("Elapsed:  " + elapsedTimeMillis)
      println("Expected: " + n.sameElements(expected))
    }
  }
}

object SortingTestPad {

  /**
   * @param args
   */
  def main(args: Array[String]) {
    var testPad = new SortingTestPad(25)

    testPad.run()
  }
}
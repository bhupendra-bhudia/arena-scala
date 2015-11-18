package arena.algorithms.sorting

import org.scalatest._

import scala.util.Random

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 09:49
 */
class SortingTestSpec extends FlatSpec with Matchers {

  val sampleSize = 25
  val emptyNums: Array[Int] = Array[Int]()
  val nums: Array[Int] = Array.fill(sampleSize)(Random.nextInt(100))

  val expectedEmpty: Array[Int] = Array[Int]()
  val expected: Array[Int] = nums.clone()
  scala.util.Sorting.quickSort(expected)

  var sorters: Array[Sortable] = Array[Sortable] (
    new BubbleSort,
    new HeapSort,
    new InsertionSort,
    new MergeSort,
    new QuickSort,
    new SelectionSort
    )

  for (sorter <- sorters) {
    val sorterName = sorter.getClass.getSimpleName

    ("A " + sorterName) should "sort array in ascending order" in {
      val testData: Array[Int] = nums.clone()
      sorter.sort(testData)
      testData should be(expected)
    }
    it should "sort an empty list" in {
      val testData: Array[Int] = emptyNums.clone()
      sorter.sort(testData)
      testData should be(expectedEmpty)
    }
  }
}

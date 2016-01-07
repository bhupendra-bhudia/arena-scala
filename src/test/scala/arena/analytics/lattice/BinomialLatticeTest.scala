package arena.analytics.lattice

import arena.analytics.lattice.BinomialLattice._
import java.time.LocalDate

import org.scalatest.{BeforeAndAfter, FunSuite}

/** *
  *
  * @author Shahbaz Chaudhary (shahbazc gmail com)
  *
  */
class BinomialLatticeTest extends FunSuite with BeforeAndAfter {
  val now = LocalDate.now()

  test("ConstantBL always returns the same thing") {
    val lat = new ConstantBL(5)
    assert(lat(0)(0) === 5)
    assert(lat(1)(0) === 5)
    assert(lat(1)(1) === 5)
    assert(lat(10)(0) === 5)
    assert(lat(10)(9) === 5)
  }

  test("PassThroughBL always returns the identity") {
    val lat = new PassThroughBL((date: LocalDate) => (idx: Int) => date)
    assert(lat(now)(0) === now)
    assert(lat(now)(1) === now)
    assert(lat(now)(2) === now)
  }

  test("GenerateBL generates the correct lattice") {
    val lat = new GenerateBL(5, 100, 1.1)
    //println(lattice)
    assert(lat(0)(0) === 100)
    assert(Math.abs(lat(4)(0) - 68.3) < 0.01)
    assert(Math.abs(lat(4)(2) - 100) < 0.01)
    assert(Math.abs(lat(4)(4) - 146.41) < 0.01)
    assert(lat.size() == 5)

  }

  test("GenerateBL generates the correct lattice for 0 days") {
    val lat = new GenerateBL(0, 100, 1.1)
    //println(lattice)
    assert(lat(0)(0) === 100)
    assert(lat.size() == 0)

  }

  test("BinomialLattice.map() returns correct lattice") {
    val lat = new GenerateBL(5, 100, 1.1).map((x: Double) => x * 2)
    //println(lattice)
    assert(lat(0)(0) === 100 * 2)
    assert(Math.abs(lat(4)(0) - 68.3 * 2) < 0.01)
    assert(Math.abs(lat(4)(2) - 100 * 2) < 0.01)
    assert(Math.abs(lat(4)(4) - 146.41 * 2) < 0.01)

  }

  test("PropagateLeftBL returns correct lattice") {
    val source = new GenerateBL(5, 100, 1.1)
    val lat = new PropagateLeftBL(source, (x: Double, y: Double) => (x + y) / 2.0)
    //println(lattice)
    //assert(lattice(0)(0) === 100)
    assert(Math.abs(lat(3)(0) - 75.47) < 0.01)
    assert(Math.abs(lat(3)(2) - 110.5) < 0.01)
    assert(Math.abs(lat(3)(3) - 133.70) < 0.01)
    assert(lat.size() == 4)
    assert(source.size() == lat.size() + 1)

  }
}
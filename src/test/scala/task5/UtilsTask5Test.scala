package task5

import org.scalatest.flatspec.AnyFlatSpec
import task5.UtilsTask5._

class UtilsTask5Test extends AnyFlatSpec {

  behavior of "UtilsTask5Test"

  val testPath = "src/test/resources/task5/test.txt"

  it should "compute" in {
//    val pointPairs = preparePointPair(testPath)
    val pointPairs = Seq(Point(0,0),Point(0,2),Point(0,2),Point(0,2))
    val result = compute(pointPairs)
    assert(result == 5)

  }

}

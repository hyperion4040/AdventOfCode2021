package task5

import org.scalatest.flatspec.AnyFlatSpec
import task5.UtilsTask5._

class UtilsTask5Test extends AnyFlatSpec {

  behavior of "UtilsTask5Test"

  val testPath = "src/test/resources/task5/test.txt"

  it should "return 1 when two points overlap" in {
    val pointPairs = Seq(Point(0,0),Point(0,2),Point(0,2),Point(0,2))
    val result = compute(pointPairs)
    assert(result == 1)
  }

  it should "should return 1 when two point pairs overlap on each other" in {

    val pointPair1 = preparePointsFromPair(PointPair(Point(0,0),Point(0,2)))
    val pointPair2 = preparePointsFromPair(PointPair(Point(0,0),Point(0,4)))
    val result = compute(pointPair1 ++ pointPair2)
    assert(result == 3)
  }

}

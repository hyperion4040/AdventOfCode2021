package task5

import org.scalatest.flatspec.AnyFlatSpec
import task5.UtilsTask5._

class UtilsTask5Test extends AnyFlatSpec {

  behavior of "UtilsTask5Test"

  val testPath = "src/test/resources/task5/test.txt"
  val realPath = "src/test/resources/task5/input.txt"

  it should "return 1 when two points overlap" in {
    val pointPairs = Seq(Point(0, 0), Point(0, 2), Point(0, 2), Point(0, 2))
    val result = compute(pointPairs)
    assert(result == 1)
  }

  it should "should return 1 when two point pairs overlap on each other" in {

    val pointPair1 = preparePointsFromPair(PointPair(Point(0, 0), Point(0, 2)))
    val pointPair2 = preparePointsFromPair(PointPair(Point(0, 0), Point(0, 4)))
    val result = compute(pointPair1 ++ pointPair2)
    assert(result == 3)
  }

  it should "should return 2 when two point in 2 dimensions pairs overlap on each other" in {

    //    val pointPair1 = preparePointsFromPair(PointPair(Point(0,0),Point(0,2)))
    //    val pointPair2 = preparePointsFromPair(PointPair(Point(0,0),Point(4,0)))
    //    val pointPair3 = preparePointsFromPair(PointPair(Point(3,0),Point(4,1)))

    //    val result = compute(pointPair1 ++ pointPair2 ++ pointPair3)
    val pointPairs = prepareAllPointsFromPairs(
      Seq(
        PointPair(Point(0, 0), Point(0, 2)),
        PointPair(Point(0, 0), Point(4, 0)),
        PointPair(Point(3, 0), Point(4, 1))
      ))
    val result = compute(pointPairs)
    assert(result == 5)
  }

  it should "return correct test value input" in {


    val lines = getLinesFromFile(testPath)

    val pointPairs = preparePointPair(lines)

    val points = prepareAllPointsFromPairs(pointPairs)

    val result = compute(points)
    assert(result == 5)
  }

  it should "return diagonal points" in {
//    val points1 = prepareDiagonalPointsFromPair(PointPair(Point(0,0),Point(2,2)))
    val points2 = prepareDiagonalPointsFromPair(PointPair(Point(9,7),Point(7,9)))

//    val result = compute(points1 ++ points2)
    val result = compute(points2)
    assert(result == 1)
  }

  it should "return correct diagonal test value input" in {


    val lines = getLinesFromFile(testPath)

    val pointPairs = preparePointPair(lines)
    val points = prepareAllPointsFromPairs(pointPairs)

    val pointDiagonals = prepareAllDiagonalPointsFromPairs(pointPairs)

    val result = compute(points ++ pointDiagonals)
    assert(result == 12)
  }

  it should "return correct diagonal real value input" in {


    val lines = getLinesFromFile(realPath)

    val pointPairs = preparePointPair(lines)

    val pointDiagonals = prepareAllDiagonalPointsFromPairs(pointPairs)

    val points = prepareAllPointsFromPairs(pointPairs)

    val result = compute(points ++ pointDiagonals,1000)
    assert(result == 5)
  }

  it should "return correct real value input" in {


    val lines = getLinesFromFile(realPath)

    val pointPairs = preparePointPair(lines)

    val points = prepareAllPointsFromPairs(pointPairs)

    val result = compute(points,1000)
    assert(result == 5)
  }

}

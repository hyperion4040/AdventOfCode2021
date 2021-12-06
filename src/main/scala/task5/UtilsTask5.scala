package task5

import scala.io.Source

object UtilsTask5 {

  def getLinesFromFile(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)

    source.getLines.toList
  }

  def preparePointPair(lines: List[String]): Seq[PointPair] = {

    lines.map {
      line => {
        val splitLine = line.split(" -> ")
        val startPoint: Point = createPoint(splitLine(0))
        val endPoint: Point = createPoint(splitLine(1))

        PointPair(startPoint, endPoint)
      }
    }

  }

  def preparePointsFromPair(pointPair: PointPair): Seq[Point] = {
    for {
      x <- pointPair.start.x to pointPair.end.x
      y <- pointPair.start.y to pointPair.end.y
    } yield Point(x,y)
  }

  private def createPoint(pointCoordinates: String) = {
    pointCoordinates.split(",") match {
      case value => Point(value(0).toInt, value(1).toInt)
    }
  }

  def compute(pointPairs: Seq[Point]): Int = {

      val grid = Grid(
        for {
          x <- 0 to 9
          y <- 0 to 9
        } yield Elem(x,y)
      )
      val temp = pointPairs
    println(temp)
      /*val t = Grid(
        pointPairs.flatMap {
          temp =>
            grid.elements.map {
              el => if (updateElemCondition(temp, el)) Elem(el.xPos, el.yPos, el.value + 1) else el
            }
        }
      )*/
      val result = Grid(grid.elements.map {
        el => Elem(el.xPos, el.yPos, updateElemValue(pointPairs, el))
      })

      println(result)


    result.elements.count(_.value >= 2)
  }


  private def updateElemValue(points: Seq[Point], el: Elem): Int = {
    points.map{
      point => if(el.xPos == point.x && el.yPos == point.y) 1 else 0
    }.sum


  }
}

case class Grid(elements: Seq[Elem])

case class Elem(xPos: Int, yPos: Int, value: Int = 0)

case class Point(x: Int, y: Int)

case class PointPair(start: Point, end: Point)

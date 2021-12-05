package task5

import scala.io.Source

object UtilsTask5 {

  def preparePointPair(filePath: String): Seq[PointPair] = {
    val source = Source.fromFile(filePath)

     val lines =  source.getLines.toList

    lines.map {
      line => {
        val splitLine = line.split(" -> ")
        val startPoint: Point = createPoint(splitLine(0))
        val endPoint: Point = createPoint(splitLine(1))

        PointPair(startPoint, endPoint)
      }
    }

  }

  private def createPoint(pointCoordinates: String) = {
    pointCoordinates.split(",") match {
      case value => Point(value(0).toInt, value(1).toInt)
    }
  }

  def compute(pointPairs: Seq[PointPair]): Int = {

      val grid = Grid(
        for {
          x <- 0 to 9
          y <- 0 to 9
        } yield Elem(x,y)
      )

      println(grid.elements.size)
    1
  }


}

case class Grid(elements: Seq[Elem])

case class Elem(xPos: Int, yPos: Int, value: Int = 0)

case class Point(x: Int, y: Int)

case class PointPair(start: Point, end: Point)

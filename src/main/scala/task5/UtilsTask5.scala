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

  def prepareAllPointsFromPairs(pointPairs: Seq[PointPair]): Seq[Point] = {
    pointPairs.filter(pointPair => pointPair.start.x == pointPair.end.x || pointPair.start.y == pointPair.end.y)
      .flatMap {
      pointPair => preparePointsFromPair(pointPair)
    }
  }

  def prepareAllDiagonalPointsFromPairs(pointPairs: Seq[PointPair]): Seq[Point] = {
    pointPairs.filterNot(pointPair => pointPair.start.x == pointPair.end.x || pointPair.start.y == pointPair.end.y)
      .flatMap{
        pointPair => prepareDiagonalPointsFromPair(pointPair)
      }
  }

  def prepareDiagonalPointsFromPair(pointPair: PointPair): Seq[Point] = {

    val xPos: Seq[Int] = for {
      x <- Math.min(pointPair.start.x,pointPair.end.x) to Math.max(pointPair.start.x,pointPair.end.x)
    } yield x

    val yPos: Seq[Int] = for {
            y <- Math.min(pointPair.start.y, pointPair.end.y) to Math.max(pointPair.start.y, pointPair.end.y)
    } yield y

    val xRes =  if(xPos.head != pointPair.start.x) xPos.reverse else xPos
    val yRes =  if(yPos.head != pointPair.start.y) yPos.reverse else yPos
    (xRes zip yRes).map (el => (Point(el._1,el._2)))

  }

  def preparePointsFromPair(pointPair: PointPair): Seq[Point] = {

    for {
      x <- Math.min(pointPair.start.x,pointPair.end.x) to Math.max(pointPair.start.x,pointPair.end.x)
      y <- Math.min(pointPair.start.y, pointPair.end.y) to Math.max(pointPair.start.y, pointPair.end.y)
    } yield Point(x,y)
  }

  private def createPoint(pointCoordinates: String) = {
    pointCoordinates.split(",") match {
      case value => Point(value(0).toInt, value(1).toInt)
    }
  }

  def compute(pointPairs: Seq[Point], gridSize: Int = 9): Int = {

      val grid = Grid(
        for {
          y <- 0 to gridSize
          x <- 0 to gridSize
        } yield Elem(x,y)
      )
      val temp = pointPairs
//    println(temp)

      val result = Grid(grid.elements.map {
        el => Elem(el.xPos, el.yPos, updateElemValue(pointPairs, el))
      })

//      println(result.elements.filter(_.value == 2))

//      println(result.elements)
//    println()
//      result.elements.map(_.value).grouped(10).toSeq.foreach(println)


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

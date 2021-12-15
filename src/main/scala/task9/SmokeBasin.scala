package task9

import scala.io.Source

object SmokeBasin {

  def getData(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)

    source.getLines.toList
  }

  def prepareData(list: List[String]): (Seq[HeatPoint], Int) = {
    val result = list.zipWithIndex.
      flatMap(el => el._1.toCharArray.zipWithIndex.map(en => HeatPoint(en._2, el._2, en._1.toString.toInt)).toSeq)

    (result, list.size)
  }

  def computeLine(elements: Seq[HeatPoint], lineNumber: Int): Seq[HeatPoint] = {
    val line = elements.filter(_.yPos == lineNumber)
    val horizontal = elements.filter(_.yPos == lineNumber+1) ++ elements.filter(_.yPos == lineNumber-1)
    val result = line.map {
      el => checkLowHeat(el, line, horizontal)

    }

    result
  }


  def computeBasins(lowPoints: Seq[HeatPoint], elements: Seq[HeatPoint]): Seq[Int] = {
    val aroundPoints = List(-1,1)

    lowPoints.head match {
      case el => aroundPoints.flatMap{
       point => {
         elements.find(_.xPos == el.xPos + point).map(_.value ) ++ elements.find(_.yPos == el.yPos + point)
       }
      }
    }

    Seq(1,1)
  }

  def computeBasin(elements: Seq[HeatPoint], numberOfLines: Int): Int = {
    val lines = 0 to numberOfLines

    val lowPoints = lines.flatMap{
      el => computeLine(elements, el)
    }.filter(_.lowPoint == true)

    val basins: Seq[Int] = computeBasins(lowPoints, elements)

    basins.sum
  }

  def compute(elements: Seq[HeatPoint], numberOfLines: Int) : Int = {

    val lines = 0 to numberOfLines

    val result = lines.flatMap {
      el => computeLine(elements, el)
    }



//    println(result)

//    println(result.filter(_.lowPoint == true))
//    result.count(_.lowPoint == true)

    result.filter(_.lowPoint == true).map{
      el => el.value + 1
    }.sum
  }

  def checkLowHeat(el: HeatPoint, line: Seq[HeatPoint], horizontal: Seq[HeatPoint]):HeatPoint = {

    val points = List(1,-1)
    val aroundValues = points.flatMap(point => line.find(_.xPos == el.xPos + point)) ++ points.flatMap(point => horizontal.find(es => es.yPos == el.yPos + point && es.xPos == el.xPos))

    val test = aroundValues.map(es => if (es.value > el.value) true else false)

    if(test.contains(false))  el else el.copy(lowPoint = true)
  }

}

case class HeatPoint(xPos: Int, yPos: Int, value: Int, lowPoint: Boolean = false)

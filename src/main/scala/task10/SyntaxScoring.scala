package task10

import scala.io.Source

object SyntaxScoring {

  def getData(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)

    source.getLines.toList
  }

  def prepareData(list: List[String]):List[Line] = {
      List(Line(list.head.toCharArray.toList))
  }

  val oppositeSides = Map(
    '<' -> '>',
    '[' -> ']',
    '(' -> ')',
    '{' -> '}'
  )

  val points = Map(
    ')' -> 3,
    ']' -> 57,
    '}' -> 1197,
    '>' -> 25137
  )

  def compute(list: List[Line]):Int = {

    computeLine(list.head.list)

  }

  def computeLine(line: List[Char]):Int = {
    line match {
      case head::tail => {
        val search = findOppositeTo(tail)
        if(head ==  search._1) computeLine(tail) else search._2
      }
      case Nil => 0
    }
  }

  def findOppositeTo(list: List[Char]): (Char,Int) = {
    val opposites = oppositeSides.values.toList

      val character = list.find(el => el == opposites.head || el == opposites(1) || el == opposites(2) || el == opposites(3)).get
      val point = points.find(_._1 == character).get._2

    (character, point)
  }

}

case class Line(list: List[Char])

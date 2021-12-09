package task8

import scala.io.Source

object SevenSegmentSearch {

  def getData(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)

    source.getLines.toList
  }

  def prepareData(list: List[String]): Seq[Segment] = {
    list.map {
      el => {
        val segment = el.split(" \\| ")(0).split(" ").toSeq.map(_.toCharArray.sortInPlace().mkString)
        val digits = el.split(" \\| ")(1).split(" ").toSeq.map(_.toCharArray.sortInPlace().mkString)
        Segment(segment, digits)
      }
    }


  }

  def compute(lines: Seq[Segment]): Int = {

    val list = lines.map {
      line => {

        line.digits.count(line.segment.filter(el => condition(el)).contains)
        //        line.segment.filter(el => condition(el)).count(line.digits.contains)
      }
    }

    println(list)


    list.sum
  }

  def computeAll(lines: Seq[Segment]): Seq[Int] = {

    lines.map {
      line => {
        val result = computeForSingle(line)
        println(result)
        result
      }
    }



  }

  private def computeForSingle(line: Segment):Int = {
    line.digits.map {
      value =>
        value.length match {
          case 2 => 1
          case 4 => 4
          case 7 => 8
          case 3 => 7
          case 6 => fromSix(value, line.segment)
          case 5 => fromFive(value, line.segment)
        }
    }.mkString.toInt

  }

  def fromFive(value: String, segments: Seq[String]): Int = {
    if (isThree(value, segments)) return 3
    if (isFive(value, segments)) return 5
     2
  }

  def fromSix(value: String, segments: Seq[String]): Int = {
    if (isNine(value, segments )) return 9
    if (isZero(value, segments )) return 0
    6;
  }

  def isFive(value: String, segments: Seq[String]): Boolean = {
    val te = segments.filter(el => el.length == 2 || el.length == 4)

    val test = te.head.diff(te(1))

    val check = isCorrect(value.toSet) _

    check(test)
  }

  def isThree(value: String, segments: Seq[String]): Boolean = {
    val check = isCorrect(value.toSet) _

    check(segments.filter(el => el.length == 3).head)
  }

  def isCorrect[A](allowed: Set[A])(s: Seq[A]) = s forall allowed

  def isZero(value: String, segments: Seq[String]): Boolean = {
    val in = segments.filter(el => el.length == 7).head.toSeq

    val inter = value.intersect(in)

    val chek = isCorrect(segments.filter(el => el.length == 2).head.toSet) _

    chek(inter)

  }

  def isNine(value: String, segments: Seq[String]): Boolean = {
    val check = isCorrect(value.toSet) _

    //    val test = check.head.diff(check(1))

    check(segments.filter(el => el.length == 4).head)
  }

  private def condition(el: String) = {
    el.length == 2 || el.length == 4 || el.length == 3 || el.length == 7
  }
}

case class Segment(segment: Seq[String], digits: Seq[String])

package task6

import scala.annotation.tailrec
import scala.io.Source

object Lanternfish {


  @tailrec
  def updateFishState(elements: Seq[Fish], numberOfDays: Long): Long = {


    numberOfDays match {
      case 0 => elements.map(_.fishNumber).sum
      case _ =>
        val newElem = elements.flatMap {
          case Fish(0L, fishNumber) => Seq(Fish(6L, fishNumber), Fish(8L, fishNumber))
          case Fish(internalTimer,fishNumber) => Seq(Fish(internalTimer-1,fishNumber))
        }

        val newElements = if(numberOfDays % 10 == 0) {
          val et = newElem.groupBy(_.internalTimer).mapValues(_.toSeq)

          et.map{
            ek => Fish(ek._1,ek._2.map(_.fishNumber).sum)
          }.toSeq


        }else {
          newElem
        }
        updateFishState(newElements, numberOfDays-1)
    }
  }

  def getData(filePath: String):List[String] = {
    val source = Source.fromFile(filePath)

    source.getLines.toList
  }

  def prepareFishSet(lines: List[String]): Seq[Fish] = {

    val value = lines.head.split(",").groupBy(identity).mapValues(_.length)
    value.map{
      el => Fish(el._1.toInt, el._2)
    }.toSeq

  }


}

case class Fish(internalTimer: Long, fishNumber: Long)

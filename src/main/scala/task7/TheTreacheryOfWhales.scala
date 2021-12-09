package task7

import scala.annotation.tailrec
import scala.io.Source

object TheTreacheryOfWhales {

  def getData(filePath: String):List[String] = {
    val source = Source.fromFile(filePath)

    source.getLines.toList
  }

  def prepareData(list: List[String]): Seq[Int] = {
    val value = list.head.split(",").toSeq.map(_.toInt)
    value
  }

  def compute(minValue: Int, maxValue: Int,  crabs: Seq[Int], possibleAnswers: Seq[Int]): Int = {

    val splitLine = Math.abs(maxValue - minValue)/2

    if(possibleAnswers.size == 2) {

      val possibleAnswersNearbyHead = crabs.filterNot(el => Math.abs(el - possibleAnswers.head) > 200 ).distinct
      val possibleAnswersNearbyLast = crabs.filterNot(el => Math.abs(el - possibleAnswers.last) > 200 ).distinct

      val preResult = possibleAnswersNearbyHead ++ possibleAnswersNearbyLast

      val result = preResult.map{
        spli => crabs.map(el => Math.abs(el - spli)).sum
      }.min

      return result
    }

    val downSum = crabs.filter(el => el > splitLine).map(el => Math.abs(el - splitLine)).sum

    val upSum = Math.abs(crabs.map(el => Math.abs(el - splitLine)).sum - downSum)



    if(upSum > downSum) {
      compute( splitLine, maxValue,  crabs,possibleAnswers :+ splitLine)
    }else {
      compute(minValue, splitLine, crabs, possibleAnswers :+ splitLine)
    }
  }

  def computeNew(minValue: Int, maxValue: Int,  crabs: Seq[Int], possibleAnswers: Seq[Int]): Int = {

    val splitLine = Math.abs(maxValue - minValue)/2



    if(possibleAnswers.size == 2) {

      val possibleAnswersNearbyHead = possibleAnswers.head - 100 to possibleAnswers.head + 100
      val possibleAnswersNearbyLast = possibleAnswers.last - 100 to possibleAnswers.last + 100

      val preResult = possibleAnswersNearbyHead ++ possibleAnswersNearbyLast

      val result = preResult.map{
        spli => crabs.map(el => factorial(Math.abs(el - spli)))
      }

      val res = result.map(el => el.sum)

      return res.min
    }

    val downSum = crabs.filter(el => el > splitLine).map(el => factorial(Math.abs(el - splitLine))).sum

    val upSum = Math.abs(crabs.map(el => factorial(Math.abs(el - splitLine))).sum - downSum)




    if(upSum > downSum) {
      computeNew( splitLine, maxValue,  crabs,possibleAnswers :+ splitLine)
    }else {
      computeNew(minValue, splitLine, crabs, possibleAnswers :+ splitLine)
    }
  }

  def factorial(n: Int): Int =
  {
    // Using tail recursion
    @tailrec def factorialAcc(acc: Int, n: Int): Int =
    {
      if (n <= 1)
        acc
      else
        factorialAcc(n + acc, n - 1)
    }
    factorialAcc(1, n)
  }

}

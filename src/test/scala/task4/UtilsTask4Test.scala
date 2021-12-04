package task4

import org.scalatest.flatspec.AnyFlatSpec

class UtilsTask4Test extends AnyFlatSpec {

  behavior of "UtilsTask3Test"

  val path = "src/test/resources/task4/test.txt"
  val realPath = "src/test/resources/task4/input.txt"

  it should "bingo" in {
    val result = UtilsTask4.bingo(path)
    assert(result == 4512)
  }

  it should "bingoReal" in {
    val result = UtilsTask4.bingo(realPath)
    assert(result == 4512)
  }
}

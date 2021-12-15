package task10

import org.scalatest.flatspec.AnyFlatSpec

class SyntaxScoringTest extends AnyFlatSpec {

  val testPath = "src/test/resources/task10/test.txt"
  val realPath = "src/test/resources/task10/input.txt"

  behavior of "SyntaxScoringTest"

  it should "compute" in {
    val data = SyntaxScoring.getData(testPath)
    val preData = SyntaxScoring.prepareData(data)

    val result = SyntaxScoring.compute(preData)

    assert(result == 0)
  }

}

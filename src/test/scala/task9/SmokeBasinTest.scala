package task9

import org.scalatest.flatspec.AnyFlatSpec

class SmokeBasinTest extends AnyFlatSpec {

  val testPath = "src/test/resources/task9/test.txt"
  val realPath = "src/test/resources/task9/input.txt"

  behavior of "SmokeBasinTest"

  it should "compute" in {
    val data = SmokeBasin.getData(testPath)
    val presSeq = SmokeBasin.prepareData(data)
    val result = SmokeBasin.compute(presSeq._1,presSeq._2)
    assert(result == 15)
  }

  it should "compute Real" in {
    val data = SmokeBasin.getData(realPath)
    val presSeq = SmokeBasin.prepareData(data)
    val result = SmokeBasin.compute(presSeq._1,presSeq._2)
    assert(result == 417)
  }




}

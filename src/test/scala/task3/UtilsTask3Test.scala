package task3

import org.scalatest.flatspec.AnyFlatSpec

class UtilsTask3Test extends AnyFlatSpec {

  behavior of "UtilsTask3Test"

  val path = "src/test/resources/task3/test.txt"
  val realPath = "src/test/resources/task3/input.csv"

  it should "computePowerConsumption" in {
    val result = UtilsTask3.computePowerConsumption(path)
    assert(result == 198)
  }


  it should "computePowerConsumptionReal" in {
    val result = UtilsTask3.computePowerConsumption(realPath)
    assert(result == 198)
  }

  it should "computerCO2" in {
    val result = UtilsTask3.computeCo2(path)
    assert(result == 230)
  }

  it should "computerCO2Real" in {
    val result = UtilsTask3.computeCo2(realPath)
    assert(result == 230)
  }

}

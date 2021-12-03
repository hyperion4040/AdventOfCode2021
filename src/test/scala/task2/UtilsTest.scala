package task2

import org.scalatest.flatspec.AnyFlatSpec

class UtilsTest extends AnyFlatSpec {

  val resourcesPath = "src/test/resources/task2/test.csv"
  val resources = "src/test/resources/task2/input.csv"

  it should "test" in {
    val result = Utils.computeForwardAndDeep(resourcesPath)
    assert(result == 150)
  }

  it should "real" in {
    val result = Utils.computeForwardAndDeep(resources)
    assert(result == 150)
  }

  it should "test2" in {
    val result = Utils.computeAndAim(resourcesPath)
    assert(result == 900)
  }

  it should "real2" in {
    val result = Utils.computeAndAim(resources)
    assert(result == 900)
  }
}

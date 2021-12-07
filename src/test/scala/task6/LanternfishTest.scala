package task6

import org.scalatest.flatspec.AnyFlatSpec

class LanternfishTest extends AnyFlatSpec {

  val testPath = "src/test/resources/task6/test.txt"
  val realPath = "src/test/resources/task6/input.txt"

  behavior of "LanternfishTest"

  it should "prepared Fish set returns elements grouped by number" in {
    val data = Lanternfish.getData(testPath)
    val result = Lanternfish.prepareFishSet(data)
    assert(result.size == 4)
    assert(result.find(_.internalTimer == 3).get.fishNumber == 2)
    assert(result.find(_.internalTimer == 1).get.fishNumber == 1)
    assert(result.find(_.internalTimer == 2).get.fishNumber == 1)
    assert(result.find(_.internalTimer == 4).get.fishNumber == 1)
  }

  it should "computeLanternFishAfter after 18 days" in {
    val data = Lanternfish.getData(testPath)
    val set = Lanternfish.prepareFishSet(data)
    val result = Lanternfish.updateFishState(set,18)
    assert(result == 26)
  }

  it should "computeLanternFishAfter after 80 days" in {
    val data = Lanternfish.getData(testPath)
    val set = Lanternfish.prepareFishSet(data)
    val result = Lanternfish.updateFishState(set,80)
    assert(result == 5934)
  }

  it should "computeLanternFishAfter Read after 80 days" in {
    val data = Lanternfish.getData(realPath)
    val set = Lanternfish.prepareFishSet(data)
    val result = Lanternfish.updateFishState(set,80)
    assert(result == 380758)
  }

  it should "computeLanternFishAfter after 256 days Part 2" in {
    val data = Lanternfish.getData(testPath)
    val set = Lanternfish.prepareFishSet(data)
    val result = Lanternfish.updateFishState(set,256)
    assert(result == 26984457539L)
  }

  it should "computeLanternFishAfter Read after 256 days Part 2" in {
    val data = Lanternfish.getData(realPath)
    val set = Lanternfish.prepareFishSet(data)
    val result = Lanternfish.updateFishState(set,256)
    assert(result == 26984457539L)
  }


}

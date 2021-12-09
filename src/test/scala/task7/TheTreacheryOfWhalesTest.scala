package task7

import org.scalatest.flatspec.AnyFlatSpec

class TheTreacheryOfWhalesTest extends AnyFlatSpec {

  val testPath = "src/test/resources/task7/test.txt"
  val realPath = "src/test/resources/task7/input.txt"

  behavior of "TheTreacheryOfWhalesTest"

  it should "compute" in {
    val data = TheTreacheryOfWhales.getData(testPath)
    val preSet = TheTreacheryOfWhales.prepareData(data)

    val result = TheTreacheryOfWhales.compute(preSet.max /2,preSet.max, preSet, Seq.empty[Int])
    assert(result == 37)
  }

  it should "compute Real" in {
    val data = TheTreacheryOfWhales.getData(realPath)
    val preSet = TheTreacheryOfWhales.prepareData(data)

    val result = TheTreacheryOfWhales.compute(preSet.max /2,preSet.max, preSet, Seq.empty[Int])
    assert(result == 39)
  }

  it should "compute part 2" in {
    val data = TheTreacheryOfWhales.getData(testPath)
    val preSet = TheTreacheryOfWhales.prepareData(data)

    val result = TheTreacheryOfWhales.computeNew(preSet.max /2,preSet.max, preSet, Seq.empty[Int])
    assert(result == 168)

  }


  it should "compute Real part 2" in {
    val data = TheTreacheryOfWhales.getData(realPath)
    val preSet = TheTreacheryOfWhales.prepareData(data)

    val result = TheTreacheryOfWhales.computeNew(preSet.max /2,preSet.max, preSet, Seq.empty[Int])
    assert(result == 168)

  }
}

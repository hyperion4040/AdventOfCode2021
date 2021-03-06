package task8

import org.scalatest.flatspec.AnyFlatSpec

class SevenSegmentSearchTest extends AnyFlatSpec {

  behavior of "SevenSegmentSearchTest"

  val testPath = "src/test/resources/task8/test.txt"
  val realPath = "src/test/resources/task8/input.txt"

  it should "compute" in {
    val data = SevenSegmentSearch.getData(testPath)
    val preSeq = SevenSegmentSearch.prepareData(data)

    val result = SevenSegmentSearch.compute(preSeq)
    assert(result == 26)
  }

  it should "compute Real" in {
    val data = SevenSegmentSearch.getData(realPath)
    val preSeq = SevenSegmentSearch.prepareData(data)

    val result = SevenSegmentSearch.compute(preSeq)
    assert(result == 26)
  }

  it should "compute part 2" in {
    val data = SevenSegmentSearch.getData(testPath)
    val preSeq = SevenSegmentSearch.prepareData(data)

    val result = SevenSegmentSearch.computeAll(preSeq)
        assert(result.sum == 61229)
    assert(result.head ==  8394)
    assert(result(1) ==  9781)
    assert(result(2) == 1197)
    assert(result(3) ==  9361)
    assert(result(4) == 4873)
    assert(result(5) ==  8418)
    assert(result(6) == 4548)
    assert(result(7) == 1625)
    assert(result(8) == 8717)
    assert(result(9) == 4315)


//    assert(result == 61229)
  }

  it should "compute Real part 2 problematic lines 2" in {
    val data = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
    val dataNew = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | dab cefabd cdfgeb eafb cagedb ab"
    val preSeq = SevenSegmentSearch.prepareData(List(dataNew))

    val result = SevenSegmentSearch.computeForSingle(preSeq.head)
    assert(result == 796401)
  }


  it should "compute Real part 2 problematic lines" in {
    val data = SevenSegmentSearch.getData(testPath)
    val preSeq = SevenSegmentSearch.prepareData(data)

    val result = SevenSegmentSearch.computeForSingle(preSeq(7))
    assert(result == 1625)
  }

  it should "compute Real part 2" in {
    val data = SevenSegmentSearch.getData(realPath)
    val preSeq = SevenSegmentSearch.prepareData(data)

    val result = SevenSegmentSearch.computeAll(preSeq)
    assert(result.sum == 26)
  }


}

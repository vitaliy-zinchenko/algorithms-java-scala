package example.sort

/**
  * @author Vitalii Zinchenko
  */
object RadixSort {
  def main(args: Array[String]): Unit = {
    val arr = Array(362, 682, 361, 754, 359, 358)
    leastRadixSort(arr, 3).foreach(println)
  }

  def leastRadixSort(arr: Array[Int], radix: Int): Array[Int] = {
    def radixValue(value: Int, radixIndex: Int): Int = {
      ((value % Math.pow(10, radixIndex)) / Math.pow(10, radixIndex - 1)).toInt
    }
    def fill(arr: Array[Int], v: Int) = for(i <- arr.indices) arr(i) = v

    val count = Array.fill(10)(0)
    var tempArr = arr

    for(r <- 1 to radix) {
      fill(count, 0)
      for(item <- tempArr) {
        val value = radixValue(item, r)
        count(value) = count(value) + 1
      }
      var nextStart = 0
      for(i <- count.indices) {
        val temp = count(i)
        count(i) = nextStart
        nextStart = nextStart + temp
      }

      val sorted = new Array[Int](tempArr.length)
      for(i <- tempArr.indices) {
        val radixVal = radixValue(tempArr(i), r)
        val index = count(radixVal)
        sorted(index) = tempArr(i)
        count(radixVal) = count(radixVal) + 1
      }
      tempArr = sorted
    }
    tempArr
  }
}

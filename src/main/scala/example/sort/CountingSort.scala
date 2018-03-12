package example.sort

/**
  * @author Vitalii Zinchenko
  */
object CountingSort {
  def main(args: Array[String]): Unit = {
    val arr =  Array(2, 3, 4, 2, 3, 4, 5, 5, 5, 3, 3, 3)
    sort(arr)
    arr.foreach(print)
  }

  def sort(arr: Array[Int]): Unit = {
    val (min, max) = minMax(arr)
    val histogram = new Array[Int](max - min + 1).map(_ => 0)
    for(i <- arr.indices) {
      val value = arr(i)
      histogram(value - min) = histogram(value - min) + 1
    }
    var current = 0
    for(i <- histogram.indices) {
      val value = i + min
      for(j <- 0 until histogram(i)) {
        arr(current) = value
        current = current + 1
      }
    }
  }

  def minMax(arr: Array[Int]): (Int, Int) = {
    var (min, max) = (arr(0), arr(0))
    for(i <- arr.indices) {
      val current = arr(i)
      if(min > current) min = current
      if(max < current) max = current
    }
    (min, max)
  }
}

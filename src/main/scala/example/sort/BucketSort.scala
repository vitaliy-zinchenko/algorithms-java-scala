package example.sort

import scala.collection.mutable

/**
  * @author Vitalii Zinchenko
  */
object BucketSort {
  def main(args: Array[String]): Unit = {
    val arr = Array(.31, .01, .52, .05, .73, .25, .67)
    val partitioner = (v: Double, count: Int) => (v * count).toInt
    sort(arr, partitioner, 0.5F)
    arr.foreach(println)
  }

  def sort[T: Ordering](arr: Array[T], partitioner: (T, Int) => Int, bucketsRatio: Float): Unit = {
    val bucketsCount = (arr.length * bucketsRatio).toInt
    val buckets = Array.fill(bucketsCount)(new mutable.MutableList[T])

    for(item <- arr)
      buckets(partitioner(item, bucketsCount)) += item

    var j = 0
    for(i <- buckets.indices) {
      val sorted = sortBucket(buckets(i))
      sorted.foreach(v => {
        arr(j) = v
        j += 1
      })
    }
  }

  private def sortBucket[T: Ordering](bucket: mutable.MutableList[T]): mutable.MutableList[T] = {
    bucket.sorted
  }



}

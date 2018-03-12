package example.sort

import scala.reflect.ClassTag

/**
  * @author Vitalii Zinchenko
  */
object QuickSort {
  def main(args: Array[String]): Unit = {
    val array = Array(1, 4, 2, 3)
    sort(array)
    array.foreach(print)

    println(s"---")

    sortFP(Array(1, 4, 2, 3)).foreach(println)
  }

  // 1, 4, 2, 3
  //          P
  // 1, 2, 4, 3
  //
  //       P

  def sort[T : Ordering](arr: Array[T]): Unit = {
    sort(arr, 0, arr.length - 1)
  }

  private def sort[T : Ordering](array: Array[T], start: Int, end: Int): Unit = {
    if(start >= end) () // base case
    else {
      val pivotIndex = partition(array, start, end)
      sort(array, start, pivotIndex - 1)
      sort(array, pivotIndex + 1, end)
    }
  }

  private def partition[T: Ordering](arr: Array[T], start: Int, end: Int): Int = {
    val pivot = arr(end)
    var minEnd = start - 1
    var maxEnd = start - 1
    for(i <- start until end) {
      if(implicitly[Ordering[T]].lt(arr(i) , pivot)) {
        minEnd = minEnd + 1
        maxEnd = maxEnd + 1
        if(minEnd != maxEnd) swap(arr, minEnd, i)
      } else {
        maxEnd = maxEnd + 1
      }
    }
    val newPivotIndex = minEnd + 1
    swap(arr, end, newPivotIndex)
    newPivotIndex
  }

  private def swap[T](arr: Array[T], from: Int, to: Int): Unit = {
    val toValue = arr(to)
    arr(to) = arr(from)
    arr(from) = toValue
  }

  def sortFP[T: Ordering: ClassTag](arr: Array[T]): Array[T] = {
    if(arr.length <= 1) arr
    else {
      val ord = implicitly[Ordering[T]]
      import ord._
      val pivot = arr(0)
      Array.concat(
        sortFP(arr.filter(pivot >)),
        arr.filter(pivot ==),
        sortFP(arr.filter(pivot <))
      )
    }
  }



}

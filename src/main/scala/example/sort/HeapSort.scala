package example.sort

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object HeapSort {

  def main(args: Array[String]): Unit = {
    //             | |     |           |  |
    val arr = Array(3, 7, 4, 8, 2, 9, 6, 1)
    sort(arr)
    arr.foreach(println)
  }

  def sort[T: Ordering](arr: Array[T]): Unit = {
    @tailrec
    def _do(heapSize: Int): Unit = if(heapSize > 0) {
      swap(arr, 0, heapSize - 1)
      reheapifyDown(arr, 0)
      _do(heapSize - 1)
    }
    buildHeap(arr)
    _do(arr.length)
  }

  private def buildHeap[T: Ordering](arr: Array[T]): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._
    @tailrec
    def _do(ind: Int): Unit = if(ind >= 0) {
      val maxCh = maxChildInd(arr, ind)
      if (arr(ind) < arr(maxCh)) {
        swap(arr, ind, maxCh)
        reheapifyDown(arr, maxCh)
      }
      _do(ind - 1)
    }

    _do((arr.length / 2) - 1)
  }

  private def maxChildInd[T: Ordering](arr: Array[T], ind: Int): Int = {
    val ord = implicitly[Ordering[T]]
    import ord._
    val (left, right) = children(ind)
    if(right >= arr.length) left
    else if(arr(left) > arr(right)) left
    else right
  }

  private def children(ind: Int): (Int, Int) = {
    val double = ind * 2
    (double + 1, double + 2)
  }

  private def swap[T](arr: Array[T], from: Int, to: Int) = {
    val toVal = arr(to)
    arr(to) = arr(from)
    arr(from) = toVal
  }

  private def reheapifyDown[T: Ordering](arr: Array[T], ind: Int) = {
    val ord = implicitly[Ordering[T]]
    import ord._
    @tailrec
    def _do(i: Int): Unit = if(i < arr.length/2) {
      val maxCh = maxChildInd(arr, i)
      if(arr(i) < arr(maxCh)) {
        swap(arr, i, maxCh)
        _do(maxCh)
      }
    }
    _do(ind)
  }
}


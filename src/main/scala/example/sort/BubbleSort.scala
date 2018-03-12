package example.sort

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object BubbleSort {
  def main(args: Array[String]): Unit = {
//    val arr = Array(4, 2, 4, 1)
//    sort(arr)
//    arr.foreach(println)
//    println("-")
//    val arr2 = Array(4, 2, 4, 1)
//    sortRecursive(arr2)
//    arr2.foreach(println)
//    println("-")
    val arr3 = Array(4, 2, 4, 1)
    sortRec2(arr3)
    arr3.foreach(println)
  }

  def swap[T](arr: Array[T], from: Int, to: Int) = {
    val toVal = arr(to)
    arr(to) = arr(from)
    arr(from) = toVal
  }


  def sort[T: Ordering](arr: Array[T]): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._

    for (i <- 0 until arr.length - 1)
      for (j <- 0 until (arr.length - i - 1))
        if (arr(j) > arr(j + 1)) swap(arr, j, j + 1)
  }

  def sortRecursive[T: Ordering](arr: Array[T]): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._

    @tailrec
    def out(arr: Array[T], current: Int): Unit = {
      if (current < 0) ()
      else {
        in(arr, 0, current)
        out(arr, current - 1)
      }
    }

    @tailrec
    def in(arr: Array[T], cur: Int, to: Int): Unit = {
      if (cur > to) ()
      else {
        if (arr(cur) > arr(cur + 1)) swap(arr, cur, cur + 1)
        in(arr, cur + 1, to)
      }
    }

    out(arr, arr.length - 2)
  }


  def sortRec2[T: Ordering](arr: Array[T]): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._

    @tailrec
    def sort(arr: Array[T], cur: Int, to: Int): Unit = {
      if (to < 0) ()
      else {
        if (arr(cur) > arr(cur + 1)) swap(arr, cur, cur + 1)
        val (newCur, newTo) = if (cur == to) (0, to - 1)
                              else (cur + 1, to)
        sort(arr, newCur, newTo)
      }
    }

    sort(arr, 0, arr.length - 2)
  }


  //  def main(args: Array[String]): Unit = {
  //    val arr = Array(1, 4, 2, 3)
  //    sort(arr)
  //    arr.foreach(print)
  //  }
  //
  //  // 0, 1, 2, 3 - indexes
  //  // 1, 4, 2, 3 - items
  //
  //  def sort[T : Ordering](arr: Array[T]): Unit = {
  //    val ord = implicitly[Ordering[T]]
  //    import ord._
  //    for(subEnd <- arr.length - 1 until(end = 0, step = -1)) {
  //      for(i <- 0 until subEnd) {
  //        if(arr(i) > arr(i + 1)) swap(arr, i, i+1)
  //      }
  //    }
  //  }
  //
  //  private def swap[T](arr: Array[T], from: Int, to: Int): Unit = {
  //    val toVal = arr(to)
  //    arr(to) = arr(from)
  //    arr(from) = toVal
  //  }

}

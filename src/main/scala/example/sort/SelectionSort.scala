package example.sort

/**
  * @author Vitalii Zinchenko
  */
object SelectionSort {

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 4, 2, 3)
    sort(arr)
    arr.foreach(print)
  }

  def sort[T: Ordering](arr: Array[T]): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._
    for (current <- arr.indices) {
      var min = current
      for (i <- current + 1 until arr.length) {
        if(arr(min) > arr(i)) min = i
      }
      if(min != current) swap(arr, min, current)
    }
  }

  private def swap[T](arr: Array[T], from: Int, to: Int): Unit = {
    print("s")
    val toVal = arr(to)
    arr(to) = arr(from)
    arr(from) = toVal
  }

}

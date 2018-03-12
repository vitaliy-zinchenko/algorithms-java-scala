package example.sort

/**
  * @author Vitalii Zinchenko
  */
object InsertionSort {

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 4, 2, 3)
    sort(arr)

    arr.foreach(print)

  }

  def sort[T: Ordering](arr: Array[T]): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._
    for (current <- 1 until arr.length) {
      if (arr(current - 1) > arr(current)) {
        var i = current
        while(i > 0 && arr(i - 1) > arr(i)) {
          swap(arr, i - 1, i)
          i = i - 1
        }
      }
    }
  }

  private def swap[T](arr: Array[T], from: Int, to: Int): Unit = {
    print("s")
    val toVal = arr(to)
    arr(to) = arr(from)
    arr(from) = toVal
  }

}

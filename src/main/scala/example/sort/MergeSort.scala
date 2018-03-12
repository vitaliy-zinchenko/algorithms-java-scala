package example.sort

/**
  * @author Vitalii Zinchenko
  */
object MergeSort {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 4, 2, 3)
    sort(arr)
    arr.foreach(print)

    println(s"-------")

    sortFP(List(1, 4, 2, 3)).foreach(println)

  }

  def sort[T: Ordering : Manifest](arr: Array[T]): Unit = {
    sort[T](arr, 0, arr.length - 1)
  }

  private def sort[T: Ordering : Manifest](arr: Array[T], start: Int, end: Int): Unit = {
    if (start < end) {
      val middle = (end + start) / 2
      sort(arr, start, middle)
      sort(arr, middle + 1, end)
      merge[T](arr, start, middle, end)
    }
  }

  private def merge[T: Manifest : Ordering](arr: Array[T], start: Int, middle: Int, end: Int): Unit = {
    val ord = implicitly[Ordering[T]]
    import ord._
    val left = subArray[T](arr, start, middle)
    val right = subArray(arr, middle + 1, end)
    var (leftIndex, rightIndex) = (0, 0)
    for (i <- start to end) {
      if (leftIndex == left.length) {
        arr(i) = right(rightIndex)
        rightIndex = rightIndex + 1
      } else if (rightIndex == right.length) {
        arr(i) = left(leftIndex)
        leftIndex = leftIndex + 1
      } else if (left(leftIndex) < right(rightIndex)) {
        arr(i) = left(leftIndex)
        leftIndex = leftIndex + 1
      } else {
        arr(i) = right(rightIndex)
        rightIndex = rightIndex + 1
      }
    }
  }

  private def subArray[T: Ordering : Manifest](arr: Array[T], from: Int, to: Int): Array[T] = {
    val newArr = new Array[T](to - from + 1)
    var newI = 0
    for (i <- from to to) {
      newArr(newI) = arr(i)
      newI = newI + 1
    }
    newArr
  }

  def sortFP[T: Ordering](list: List[T]): List[T] = {
    val ord = implicitly[Ordering[T]]
    import ord._
    if (list.length <= 1) list
    else {
      def merge(left: List[T], right: List[T]): List[T] = (left, right) match {
        case (left, Nil) => left
        case (Nil, right) => right
        case (headLeft :: tailLeft, headRight :: tailRight) =>
          if(headLeft < headRight) headLeft :: merge(tailLeft, right)
          else headRight :: merge(left, tailRight)
      }

      val (left, right) = list.splitAt(list.length / 2)
      merge(sortFP(left), sortFP(right))
    }
  }
}


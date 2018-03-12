package example.data_structures

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object Heap {

  def main(args: Array[String]): Unit = {
    val heap = new Heap[Int]()
    heap.add(8)
    heap.add(4)
    heap.add(7)
    heap.add(3)
    heap.add(9)

    println(heap)

    val small = heap.getAndRemove()

    println(heap)
  }

}

class Heap[T: Manifest : Ordering](capacity: Int = 16) {

  private val ordT = implicitly[Ordering[T]]

  import ordT._

  var arr = new Array[T](capacity)
  var size = 0

  private def resizeIfNeeded() = {
    if (arr.length == size) {
      val newArr = new Array[T](arr.length * 2)
      System.arraycopy(arr, 0, newArr, 0, arr.length)
      arr = newArr
    }
  }

  private def reheapifyUp() = {
    @tailrec
    def _do(index: Int): Unit = if (index != 0) {
      val parent = parentIndex(index)
      if (greater(parent, index)) {
        swap(index, parent)
        _do(parent)
      }
    }

    _do(size - 1)
  }

  private def reheapifyDown() = {
    @tailrec
    def _do(index: Int): Unit = {
      val (left, right) = children(index)
      if(inBoundaries(left) && greater(index, left)) {
        swap(index, left)
        _do(left)
      } else if(inBoundaries(right) && greater(index, right)) {
        swap(index, right)
        _do(right)
      }
    }
    _do(0)
  }

  private def greater(first: Int, second: Int): Boolean = arr(first) > arr(second)

  private def inBoundaries(index: Int): Boolean = index < size

  private def parentIndex(index: Int): Int = {
    val half = index / 2
    if (index % 2 == 0) half - 1
    else half
  }

  type LeftRightChildIndex = (Int, Int)

  private def children(index: Int): LeftRightChildIndex = {
    val double = index * 2
    (double + 1, double + 2)
  }

  private def swap(from: Int, to: Int) = {
    val toValue = arr(to)
    arr(to) = arr(from)
    arr(from) = toValue
  }

  def add(value: T): Unit = {
    resizeIfNeeded()
    arr(size) = value
    size += 1
    reheapifyUp()
  }

  def getAndRemove(): T = {
    resizeIfNeeded()
    val ret = arr(0)
    swap(size - 1, 0)
    size -= 1
    reheapifyDown()
    ret
  }

  override def toString: String = {
    val sb = new StringBuilder
    sb.append("[")
    for(i <- 0 until size) {
      sb.append(arr(i))
      if(i < size - 1) sb.append(", ")
    }
    sb.append("]")
    sb.toString()
  }
}

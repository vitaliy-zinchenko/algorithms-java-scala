package example.crack._3_stack_queue

/**
  * @author Vitalii Zinchenko
  */
object Queue {

  def main(args: Array[String]): Unit = {
    val q = new Queue[Int]

    q.add(1).add(2).add(3)
//                      ^ last

    println(q.peek())
    println(q.remove())
    println(q.peek())
    println(q.peek())

  }



}

class Queue[T] {

  private class Node(val data: T, var next: Node = null)

  private var first: Node = _
  private var last: Node = _

  def add(item: T): this.type = {
    val node = new Node(item)

    if (first == null) {
      first = node
      last = node
    } else {
      last.next = node
      last = node
    }
    this
  }

  def remove(): this.type = {
    checkFirst()

    if (first.eq(last)) {
      first = null
      last = null
    } else {
      first = first.next
    }
    this
  }

  def peek(): T = {
    checkFirst()

    val t = first.data
    remove()
    t
  }

  private def checkFirst() = if (first == null) throw new IllegalStateException()

  def isEmpty: Boolean = first == null
}

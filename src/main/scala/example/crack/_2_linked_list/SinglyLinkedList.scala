package example.crack._2_linked_list

import scala.annotation.tailrec
import scala.collection.mutable

/**
  * @author Vitalii Zinchenko
  */
object SinglyLinkedList {
  def main(args: Array[String]): Unit = {
    //    val l = new SinglyLinkedList[Int]
    //    l.addToHead(5)
    //    l.addToHead(5)
    //    l.addToHead(5)
    //    l.addToHead(5)
    //    l.addToHead(5)
    //
    //    println(l)
    //
    //    println(l.deduplicateN2())
    //
    //    println(l)


    val l = new SinglyLinkedList[Int]
    l.addToHead(5)
    l.addToHead(4)
    l.addToHead(3)
    l.addToHead(2)
    l.addToHead(1)
    l.addToHead(0)

    println(l.getKthFromEnd(2))
    println(l.getKthFromEnd(5))
    println(l.getKthFromEnd(0))
//    println(l.getKthFromEnd(-1))
//    println(l.getKthFromEnd(6))
  }
}

class SinglyLinkedList[T] {

  private case class Node(var data: T, var next: Node)

  private var root: Node = _

  private var size = 0

  def addToHead(data: T): Unit = {
    root = Node(data, root)
    size += 1
  }

  def get(index: Int): T = {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException

    @tailrec
    def _get(node: Node, i: Int): T = {
      if (i == index) node.data
      else _get(node.next, i + 1)
    }

    _get(root, 0)
  }

  def delete(data: T): Boolean = {
    /*
    def _delete(node: Node): Boolean = node match {
      case n if n == null => false
      case Node(value, null) if value == data => {
        root = null
        size -= 1
        true
      }
      case n1 @ Node(_, n2 @ Node(value, n)) if value == data => {
        n1.next = n
        size -= 1
        true
      }
      case _ => _delete(node.next)
    }

    _delete(root)
    */

    /*
    var node = root
    var prev: Node = null
    while (node != null) {
      if (node.data == data) {
        if (prev == null) root = node.next
        else prev.next = node.next
        size -= 1
        return true
      }
      prev = node
      node = node.next
    }
    */


    if (root == null) false
    else if (root.data == data) {
      root = root.next
      true
    } else {
      var node = root
      while (node.next != null) {
        if (node.next.data == data) {
          node.next = node.next.next
          size -= 1
          return true
        } else
          node = node.next
      }
      false
    }
  }

  def deduplicateN(): Unit =
    if (root != null) {
      val set = new mutable.HashSet[T]()
      set += root.data
      var node = root
      while (node != null && node.next != null) {
        if (set.contains(node.next.data)) { // is duplicate
          node.next = node.next.next
          size -= 1
        } else {
          set += node.next.data
          node = node.next
        }
      }
    }

  def deduplicateN2(): Unit = {
    def deduplicate(startNode: Node, value: T) = if (startNode != null) {
      var node = startNode
      while (node != null && node.next != null) {
        if (value == node.next.data) {
          node.next = node.next.next
          size -= 1
        } else {
          node = node.next
        }
      }
    }

    if (root != null) {
      var node = root
      while (node != null) {
        deduplicate(node, node.data)
        node = node.next
      }
    }
  }

  def getKthFromEndRecursive(k: Int): T = {
    def _do(node: Node): (Int, Option[T]) = {
      if (node == null) (0, None)
      else {
        val (_k, value) = _do(node.next)
        if (_k == k) (_k + 1, Some(node.data))
        else (_k + 1, value)
      }
    }

    val (_, value) = _do(root)
    if(value.isEmpty) throw new IndexOutOfBoundsException
    else value.get
  }

  def getKthFromEnd(k: Int): T = {
    if(k < 0) throw new IndexOutOfBoundsException

    var fast = root
    var slow = root
    var delta = 0
    while(fast.next != null) {
      if(delta >= k) slow = slow.next
      fast = fast.next
      delta += 1
    }

    if(fast.next == null && delta < k) throw new IndexOutOfBoundsException
    else slow.data
  }

  override def toString: String = {
    val sb = new StringBuilder
    sb.append("[")
    var node = root
    var i = 0
    while (node != null) {
      sb.append(node.data)
      if (i < size - 1) sb.append(", ")
      i += 1
      node = node.next
    }
    sb.append("]")
    sb.toString()
  }
}


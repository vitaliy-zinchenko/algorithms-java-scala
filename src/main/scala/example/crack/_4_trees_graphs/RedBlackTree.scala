package example.crack._4_trees_graphs

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object RedBlackTree {
  def main(args: Array[String]): Unit = {
    val tree = new RedBlackTree[Int, String]()

    tree.put(10, "10")
    tree.put(5, "5")
    tree.put(15, "15")
    tree.put(3, "3")
    tree.put(1, "1")

    println("-")
  }
}

class RedBlackTree[K: Ordering, V] {
  val ord = implicitly[Ordering[K]]

  import ord._

  private class Node(val key: K) {
    var value: V = _
    var isRed: Boolean = true
    var left: Node = _
    var right: Node = _
  }

  private var root: Node = _

  def get(key: K): Option[V] = {
    @tailrec
    def _do(node: Node): Option[V] = {
      if (node == null) None
      else if (node.key == key) Some(node.value)
      else if (key < node.key) _do(node.left)
      else _do(node.right)
    }

    _do(root)
  }

  def put(key: K, value: V): Unit = {
    def _do(node: Node): Node = {
      var newNode = if (node == null) new Node(key) else node

      if (newNode.key == key) { // key found - update value or insert value to just added node
        newNode.value = value
        newNode
      } else { // go down with rebalancing
        if (key < newNode.key) {
          newNode.left = _do(newNode.left)
        } else {
          newNode.right = _do(newNode.right)
        }

        if (isBlack(newNode.left) && isRed(newNode.right)) newNode = rotateLeft(newNode)
        if (isRed(newNode.left) && isRed(newNode.left.right)) newNode.left = rotateLeft(newNode.left)
        if (isRed(newNode.left) && isRed(newNode.left.left)) newNode = rotateRight(newNode)
        if (isRed(newNode.left) && isRed(newNode.right)) flipChildColors(newNode)

        newNode
      }
    }

    root = _do(root)
    root.isRed = false // make root black
  }

  private def isRed(node: Node): Boolean = node != null && node.isRed

  private def isBlack(node: Node): Boolean = node == null || !node.isRed

  private def rotateLeft(node: Node): Node = {
    val newRoot = node.right
    node.right = newRoot.left
    newRoot.left = node
    swapColors(newRoot, node)
    newRoot
  }

  private def rotateRight(node: Node): Node = {
    val newRoot = node.left
    node.left = newRoot.right
    newRoot.right = node
    swapColors(newRoot, node)
    newRoot
  }

  def swapColors(n1: Node, n2: Node): Unit = {
    val c1 = n1.isRed
    n1.isRed = n2.isRed
    n2.isRed = c1
  }

  private def flipChildColors(node: Node): Unit = {
    node.isRed = true
    node.left.isRed = false
    node.right.isRed = false
  }

}

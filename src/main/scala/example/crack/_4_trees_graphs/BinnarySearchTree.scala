package example.crack._4_trees_graphs

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object BinarySearchTree {
  def main(args: Array[String]): Unit = {
    val tree = new BinarySearchTree[Int, String]()

    tree.put(10, "a")
    tree.put(5, "b")
    tree.put(7, "c")
    tree.put(3, "d")
    tree.put(2, "e")
    tree.put(6, "f")

    /*
           10
          /
         5
        / \
       3  7
      /  /
     2  6


     */


    println(tree.get(6))
    println(tree.get(8))

    println("-")

    tree.inOrderTraversal((k, v) => print(k))
    println("")
    tree.preOrderTraversal((k, v) => print(k))
    println("")
    tree.postOrderTraversal((k, v) => print(k))
  }
}

class BinarySearchTree[K: Ordering, V] {

  val ord = implicitly[Ordering[K]]
  import ord._

  private class Node(val key: K) {
    var value: V = _
    var left: Node = _
    var right: Node = _
  }

  private var root: Node = _

  def put(key: K, value: V): Unit = {
    def _do(node: Node): Node = {
      val newNode = if (node == null) new Node(key) else node
      traverseNode(newNode)
    }

    @inline
    def traverseNode(node: Node): Node = node.key match {
      case nodeKey if nodeKey == key  => node.value = value; node
      case nodeKey if key < nodeKey   => node.left = _do(node.left); node
      case _                          => node.right = _do(node.right); node
    }

    root = _do(root)
  }

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

  def inOrderTraversal(f: (K, V) => Unit): Unit = {
    def _do(node: Node): Unit = if(node != null) {
      _do(node.left)
      f(node.key, node.value)
      _do(node.right)
    }
    _do(root)
  }

  def preOrderTraversal(f: (K, V) => Unit): Unit = {
    def _do(node: Node): Unit = if(node != null) {
      f(node.key, node.value)
      _do(node.left)
      _do(node.right)
    }
    _do(root)
  }

  def postOrderTraversal(f: (K, V) => Unit): Unit = {
    def _do(node: Node): Unit = if(node != null) {
      _do(node.left)
      _do(node.right)
      f(node.key, node.value)
    }
    _do(root)
  }

}

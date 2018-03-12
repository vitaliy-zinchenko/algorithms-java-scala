package example.crack._4_trees_graphs

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @author Vitalii Zinchenko
  */
object Trie {
  def main(args: Array[String]): Unit = {
    val trie = new Trie[Int]
    trie.put("try", 4)
    trie.put("tree", 3)
    trie.put("ta", 1)
    trie.put("tr", 10)

//    println(trie.get("tr"))
//    println(trie.get("try"))
//    println(trie.get("trys"))
//
//    println(trie.longestPrefixOf("ff"))
//    println(trie.longestPrefixOf("tres"))

    println(trie.keysWithPrefix("tan").mkString(", "))
    println(trie.keysWithPrefix("t").mkString(", "))
    println(trie.keysWithPrefix("tr").mkString(", "))
    println(trie.keysWithPrefix("tre").mkString(", "))

    println(s"-")
  }
}

class Trie[V] {

  private class Node {
    var value: V = _
    val children = new mutable.HashMap[Char, Node]()
    def hasValue: Boolean = value != null
  }

  private var root: Node = _

  def put(key: String, value: V): Unit = {
    def _do(node: Node, depth: Int, keyTail: List[Char]): Node = {
      val newNode = if (node == null) new Node() else node
      if (key.length == depth) {
        newNode.value = value
      } else {
        val subNode = newNode.children.getOrElse(keyTail.head, null)
        newNode.children(keyTail.head) = _do(subNode, depth + 1, keyTail.tail)
      }
      newNode
    }

    root = _do(root, 0, key.toList)
  }

  private def get(key: String): Option[V] = getNode(key).map(_.value)

  private def getNode(key: String): Option[Node] = {
    @tailrec
    def _do(node: Node, depth: Int, keyTail: List[Char]): Option[Node] = {
      if (node == null) None
      else if (key.length == depth) Option(node)
      else _do(node.children.getOrElse(keyTail.head, null), depth + 1, keyTail.tail)
    }

    _do(root, 0, key.toList)
  }

  def longestPrefixOf(key: String): Option[String] = {
    @tailrec
    def _do(node: Node, keyTail: List[Char], longest: List[Char]): List[Char] = {
      if (node == null || keyTail.isEmpty) longest
      else
        node.children.get(keyTail.head) match {
          case None => longest
          case Some(child) => _do(child, keyTail.tail, keyTail.head :: longest)
        }
    }

    _do(root, key.toList, Nil) match {
      case Nil => None
      case value => Some(value.reverse.mkString)
    }
  }

  def keysWithPrefix(prefix: String): Iterator[String] = {
    def _do(node: Node, prefixList: List[Char], buffer: mutable.Buffer[String]): Unit = {
      for((key, node) <- node.children.iterator) {
        val newPrefixList = key :: prefixList
        if(node.hasValue) {
          buffer += newPrefixList.reverse.mkString
        }
        _do(node, newPrefixList, buffer)
      }
    }

    val buffer = new ListBuffer[String]
    getNode(prefix) match {
      case None => buffer.iterator
      case Some(node) => {
        _do(node, prefix.toList.reverse, buffer)
        buffer.iterator
      }
    }

  }


}

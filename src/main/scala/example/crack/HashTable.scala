package example.crack

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object HashTable {
  def main(args: Array[String]): Unit = {
    val map = new HashTable[String, String](10)
    map.put("k1", "v1")

    val v1 = map.get("k1")

    println(v1)

    println(s"--")
  }
}

class HashTable[K: Manifest, V](capacity: Int) {
  private val arr = new Array[LinkList](capacity)

  def put(key: K, value: V): Unit = {
    val index = getIndex(key)
    val list = if (arr(index) == null) {
      val newList = new LinkList
      arr(index) = newList
      newList
    } else
      arr(index)

    list.put(key, value)
  }

  def get(key: K): Option[V] = {
    arr(getIndex(key)).get(key)
  }

  private def getIndex(key: K): Int = {
    key.hashCode() % capacity
  }

  private class LinkList() {

    private var root: Option[Node] = Option.empty

    def get(key: K): Option[V] = {
      @tailrec
      def traceGet(key: K, node: Option[Node]): Option[V] = node match {
        case None => None
        case Some(node) if node.entry.key == key => Some(node.entry.value)
        case _ => traceGet(key, node.flatMap(_.next))
      }

      traceGet(key, root)
    }

    def put(key: K, value: V): Unit = {
      @tailrec
      def tracePut(key: K, nodeOption: Option[Node]): Unit = nodeOption match {
        case None => root = Some(Node(Entry(key, value), None))
        case Some(node) if node.entry.key == key => node.entry.value = value
        case Some(node@Node(_, None)) => node.next = Some(Node(Entry(key, value)))
        case _ => tracePut(key, nodeOption.flatMap(_.next))
      }

      tracePut(key, root)
    }

    private case class Node(entry: Entry, var next: Option[Node] = None)

    private case class Entry(key: K, var value: V)

  }


}

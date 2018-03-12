package example

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @author Vitalii Zinchenko
  */
object CountingSort {
  def main(args: Array[String]): Unit = {
    val str = "ABCCA"
    val res = "AABCC"
    val result = sort(str)
    println(s"$result")
  }

  def sort(in: String): String = {
    val dictionary = in.toCharArray.array.groupBy(identity).mapValues(_.length)
    val sortedChars = dictionary.keys.toList.sorted

    sortedChars.flatMap(char => {
      val count = dictionary(char)
      (0 until count).map(_ => char)
    }).mkString
  }


}

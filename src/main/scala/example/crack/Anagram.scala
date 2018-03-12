package example.crack

import scala.collection.mutable

/**
  * @author Vitalii Zinchenko
  */
object Anagram {
  def main(args: Array[String]): Unit = {
    println(isAnagram1("SAVE", "VASE"))
    println(isAnagram2("SAVE", "VASE"))
    println(isAnagram2("SAV", "VASE"))
    println(isAnagram2("SAVE", "VAS"))
    println(isAnagram2("SAVE", "VAEE"))
  }

  def isAnagram1(value1: String, value2: String): Boolean = {
    value1.toCharArray.sorted sameElements value2.toCharArray.sorted
  }

  def isAnagram2(value1: String, value2: String): Boolean = {
    value1.length == value2.length && {
      val index = new mutable.HashMap[Char, Int]()

      def addToIndex(c: Char) = index(c) = index.getOrElse(c, 0) + 1

      value1.foreach(c => addToIndex(c))

      def decrementAndCheckInIndex(c: Char): Boolean = index.get(c) match {
        case None => false
        case Some(count) => index(c) = count - 1; true
      }

      value2.forall(decrementAndCheckInIndex) && index.forall { case (key, value) => value == 0 }
    }
  }
}

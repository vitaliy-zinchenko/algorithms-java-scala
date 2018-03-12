package example.crack

import scala.annotation.tailrec

/**
  * @author Vitalii Zinchenko
  */
object Palindrome {

  def main(args: Array[String]): Unit = {
    println(isPalindrome("qwwq"))
    println(isPalindrome("qwq"))
    println(isPalindrome("qq"))
    println(isPalindrome("q"))
    println(isPalindrome(""))
    println(isPalindrome("qw"))
  }


  def isPalindrome(value: String): Boolean = {
    @tailrec
    def check(value: String, start: Int, end: Int): Boolean = {
      if(end - start <= 0) true
      else if(value(start) != value(end)) false
      else check(value, start + 1, end - 1)
    }

    if(value == null) throw new NullPointerException
    else if(value == "") true
    else check(value, 0, value.length - 1)
  }
}

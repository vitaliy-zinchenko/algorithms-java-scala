package example.crack

/**
  * @author Vitalii Zinchenko
  */
object StringBuilder {
  def main(args: Array[String]): Unit = {
    val sb = new StringBuilder(2)
    sb.append("q")
    sb.append("a")
    sb.append("z")
    sb.append("12345-")

    println(sb.toString)
  }
}

class StringBuilder(val capacity: Int = 16, val factor: Int = 2) {
  private var count = 0
  private var arr = new Array[Char](capacity)

  def append(str: String): StringBuilder = {
    if(str != null && str.length == 0) this
    else {
      val adjustedStr = if(str == null) "null" else str
      val newCount = count + adjustedStr.length
      if(newCount > count) resize(newCount)
      System.arraycopy(adjustedStr.toCharArray, 0, arr, count, adjustedStr.length)
      count += adjustedStr.length
      this
    }
  }

  private def resize(newCount: Int): Unit = {
    val doubleCapacity = capacity * factor
    val newCapacity = if(newCount > doubleCapacity) newCount else doubleCapacity
    val newArr = new Array[Char](newCapacity)
    System.arraycopy(arr, 0, newArr, 0, arr.length)
    arr = newArr
  }

  override def toString: String = {
    new String(arr, 0, count)
  }

}

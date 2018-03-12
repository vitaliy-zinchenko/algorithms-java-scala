package example.crack

/**
  * @author Vitalii Zinchenko
  */
object ArrayList {
  def main(args: Array[String]): Unit = {
    val al = new ArrayList[String](2, 2)
    al.add("1")
    al.add("2")
    al.add("3")
    al.add("4")
    al.add("5")
    al.add("6")
    al.add("7")
  }


}

class ArrayList[T: Manifest](val factor: Int = 2, val init: Int = 10) {
  private var _size: Int = 0
  private var arr: Array[T] = new Array[T](init)

  def add(value: T): Unit = {
    if(arr.length == _size) resize()
    arr(_size) = value
    _size += 1
  }

  private def resize(): Unit = {
    val newArray = new Array[T](_size * factor)
    System.arraycopy(arr, 0, newArray, 0, arr.length)
    arr = newArray
  }

  def get(index: Int): T = {
    if(index >= _size) throw new IndexOutOfBoundsException()
    arr(index)
  }

  def size(): Int = {
    _size
  }

}

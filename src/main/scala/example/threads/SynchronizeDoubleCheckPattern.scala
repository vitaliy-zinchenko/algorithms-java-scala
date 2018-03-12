package example.threads

/**
  * @author Vitalii Zinchenko
  */
object SynchronizeDoubleCheckPattern {

  @volatile private var v: String = _

  def main(args: Array[String]): Unit = {
    println(getV())
  }

  def getV(): String = {
    if(v == null) {
      SynchronizeDoubleCheckPattern.synchronized {
        if(v == null) v = "!"
        v
      }
    } else v
  }



}

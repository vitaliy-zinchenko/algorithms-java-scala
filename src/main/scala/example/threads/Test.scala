package example.threads

import scala.util.Try

/**
  * @author Vitalii Zinchenko
  */
object Test {
  def main(args: Array[String]): Unit = {
    val m1 = new AnyRef
    val m2 = new AnyRef
    val t1 = new Thread(() => {
      println(s"1.")
      m1.synchronized {
        println(s"1..")
        m2.synchronized {
          println(s"1!")
        }
      }
    })
    val t2 = new Thread(() => {
      println(s"2.")
      m2.synchronized {
        println(s"2..")
        m1.synchronized {
          println(s"2!")
        }
      }
    })

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(s"finish!")
  }
}

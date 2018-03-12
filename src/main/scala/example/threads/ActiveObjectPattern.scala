package example.threads

import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}

/**
  * @author Vitalii Zinchenko
  */
object ActiveObjectPattern {

  def main(args: Array[String]): Unit = {
    val ao = new ActiveObject
    val c = new Client(ao)
    c.play()
  }

  class ActiveObject {

    private var value: Int = 0

    private val queue: BlockingQueue[UnitTask] = new LinkedBlockingQueue[UnitTask]

    private val thread = {
      val t = new Thread(new Runnable {
        override def run(): Unit = {
          while (true) {
            queue.take().run()
            println(s"value: $value")
          }
        }
      })
      t.start()
      t
    }

    def increment(): Unit = {
      queue.put(() => value += 1)
    }

    def decrement(): Unit = {
      queue.put(() => value -= 1)
    }
  }

  class Client(activeObject: ActiveObject) {
    def play(): Unit = {
      activeObject.increment()
      activeObject.increment()
      activeObject.increment()
      activeObject.decrement()
    }
  }

  trait UnitTask {
    def run(): Unit
  }

}

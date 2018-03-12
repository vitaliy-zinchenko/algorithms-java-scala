package threds;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Vitalii Zinchenko
 */
public class LiveLocksTest {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = new LinkedBlockingQueue<>();
        queue.add("1!");
        queue.add("1");

        Thread thread = new Thread(() -> {
            boolean stop = false;
            while (!stop && !Thread.interrupted()) {
                System.out.println("..-" + Thread.interrupted());

                try {
                    String s = queue.peek();
                    int i = Integer.parseInt(s);
                    System.out.println(i);
                    queue.remove();
                } catch (NumberFormatException e) {
                    System.out.println("number format exception");
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    stop = true;
                }
            }
        });
        thread.start();

//        Thread.sleep(1000L);

//        thread.interrupt();

        System.out.println("!!!!!!");
    }
}

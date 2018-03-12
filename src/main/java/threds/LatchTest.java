package threds;

import java.util.concurrent.CountDownLatch;

/**
 * @author Vitalii Zinchenko
 */
public class LatchTest {
    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        CountDownLatch start = new CountDownLatch(n);
        CountDownLatch finish = new CountDownLatch(n);


        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                System.out.println("Ready...");
                start.countDown();
                try {
                    start.await();
                } catch (InterruptedException e) {
                    return;
                }

                System.out.println("Work: " + Thread.currentThread().getName());

                finish.countDown();
            }).start();
        }

//        Thread.sleep(1000L);
//        System.out.println("Fire");
//        start.countDown();

        finish.await();
        System.out.println("Complete");

    }
}

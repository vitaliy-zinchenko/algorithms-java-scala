package threds;

/**
 * @author Vitalii Zinchenko
 */
public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Reader reader = new Reader(calc);


//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        reader.start();
        calc.start();
    }
}

class Reader extends Thread {

    private boolean stop;
    private Calculator calculator;

    Reader(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        System.out.println("R > sync");
        synchronized (calculator) {
            System.out.println("R > is finished");
            while (!calculator.isFinished()) {
                try {
                    System.out.println("R > waiting");
                    calculator.wait();
                } catch (InterruptedException e) {
                    System.out.println("R > interrupted");
                    stop = true;
                }
            }
            System.out.println("R > getting Sum");
            System.out.println(calculator.getSum());


        }
    }
}

class Calculator extends Thread {

    private int sum;
    private boolean finished;

    @Override
    public void run() {
        System.out.println("C > sync");
        synchronized (this) {
            System.out.println("C > calculating");
            for (int i = 0; i < 10; i++) {
                sum++;
            }
            System.out.println("C > notifying");
            finished = true;
            this.notify();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public int getSum() {
        return sum;
    }
}
package thread.runnable;

public class MyRunnable implements Runnable {

    public static void main(String[] args) {
        new Thread(new MyRunnable("C")).start();
        new Thread(new MyRunnable("D")).start();
        System.out.println("shutdown!");
    }

    private String name = null;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int result = 0;
        for (int j = 101; j >= 0; j /= 3) {
            result = result / j;
        }
        System.out.println("Thread " + this.name + " is running.");
    }
}

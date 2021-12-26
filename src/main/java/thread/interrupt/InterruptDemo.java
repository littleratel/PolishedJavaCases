package thread.interrupt;

import org.junit.Test;
import util.SleepUtil;

public class InterruptDemo {

    @Test
    public void main() {
        MyThread thread = new MyThread();
        thread.start();

        SleepUtil.sleep(1);
        thread.interrupt();

        SleepUtil.sleep(1);
        System.out.println("Thread is interrupted: " + thread.isInterrupted());
        System.out.println("main线程执行完成");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        while (true) {
            if (this.interrupted()) {
                System.out.println(Thread.currentThread().getName() + " was interrupted!");
                break;
            }
//            SleepUtil.sleep(1);
        }
        System.out.println("mythread执行完成");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
    }
}

//当执行interrupted()方法后，抛出InterruptedException异常
class MyThread_A extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                if (this.interrupted()) {
                    System.out.println("myThread线程被中断");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();//这样处理比较好
        }
    }
}


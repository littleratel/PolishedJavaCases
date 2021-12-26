package thread.deadlock;

import java.util.Set;

public class DeadLocks {
    public static void main(String[] args) {
        Object oa = new Object();
        Object ob = new Object();

        new Thread(() -> {
            demo1(oa, ob);
        }).start();

        new Thread(() -> {
            demo1(ob, oa);
        }).start();

        while (Thread.activeCount() > 2) ;

        System.out.println("End Test.");
    }

    public static void demo1(Object oA, Object oB) {
        synchronized (oA) {
            System.out.println(oA.toString());
            synchronized (oB) {
                System.out.println(oB.toString());
            }
        }
    }


    class A {
        private Set<B> bs;
        public void add(B b) {
            bs.add(b);
        }

        public synchronized Set getB() {
            return bs;
        }

        public synchronized void doSvc1() {
            // do something
            for (B b : bs) {
                b.doSvc2();
            }
        }
    }

    class B {
        private A a;

        public void setA(A a) {
            this.a = a;
        }

        public synchronized void doSvc1() {
            // do svc
            a.getB();
        }

        public synchronized void doSvc2() {

        }
    }
}

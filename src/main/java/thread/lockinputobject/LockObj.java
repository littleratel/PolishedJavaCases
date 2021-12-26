package thread.lockinputobject;

public class LockObj {
    public String say() {
        String threadName = Thread.currentThread().getName();
        return threadName + " get this Locker!";
    }
}

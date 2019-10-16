package designpattern.observer;

import java.util.Observer;

public class JdkObserverTest {

    public static void main(String[] args) {
        Observer watcher = new Watcher();

        Subject publisher = new Subject();
        publisher.addObserver(watcher);
        publisher.changeData("first");
        publisher.changeData("second");
        publisher.changeData("third");
        publisher.changeData("fourth");
    }
}
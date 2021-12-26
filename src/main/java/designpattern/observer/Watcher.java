package designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者，实现jdk中的Observer
 */
public class Watcher implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Watcher: 数据改变成了：" + arg);
    }
}
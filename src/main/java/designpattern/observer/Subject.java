package designpattern.observer;

import java.util.Observable;

public class Subject extends Observable {
    private String data = "";

    public void changeData(String data) {
        if (this.data.equals(data)) {
            return;
        }
        this.data = data;

        setChanged();
        notifyObservers(this.data);
    }
}
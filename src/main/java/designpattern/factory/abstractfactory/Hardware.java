package designpattern.factory.abstractfactory;

public interface Hardware {
    Keyboard createKyeBoard();
    Mouse createMouse();
}

interface Keyboard {
    void input();
}

interface Mouse {
    void click();
}
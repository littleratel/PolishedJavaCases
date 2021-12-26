package designpattern.factory.abstractfactory;

public class MacFactory implements Hardware {
    @Override
    public Keyboard createKyeBoard() {
        return new MacKeyboard();
    }

    @Override
    public Mouse createMouse() {
        return new MacMouse();
    }
}

class WinFactory implements Hardware {
    @Override
    public Keyboard createKyeBoard() {
        return new WinKeyboard();
    }

    @Override
    public Mouse createMouse() {
        return new WinMouse();
    }
}

package designpattern.factory.abstractfactory;

public class Instances {
}

class MacKeyboard implements Keyboard {
    @Override
    public void input() {
        System.out.println("Mac 专用键盘");
    }
}

class MacMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Mac 专用鼠标");
    }
}

class WinKeyboard implements Keyboard {
    @Override
    public void input() {
        System.out.println("Win 专用键盘");
    }
}

class WinMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("win 专用鼠标");
    }
}
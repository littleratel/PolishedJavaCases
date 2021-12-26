package designpattern.factory.abstractfactory;

public class MainTest {
    public static void main(String[] args) {
        Hardware macFactory = new MacFactory();
        Keyboard keyboard = macFactory.createKyeBoard();
        keyboard.input();   //Mac 专用键盘

        Hardware winFactory = new WinFactory();
        Mouse mouse = winFactory.createMouse();
        mouse.click();  //win 专用鼠标
    }
}


package cn.bin.classextend;

import org.junit.Test;

public class ClassExtendTest {

    private class Father {
        public int money = 1;

        public Father() {
            this.money = 2;
            this.showMoney();
        }

        public void showMoney() {
            System.out.println("Father have money:" + this.money);
        }
    }

    private class Son extends Father {
        public int money = 3;

        public Son() {
            this.money = 4;
            this.showMoney();
        }

        @Override
        public void showMoney() {
            System.out.println("Son have money:" + this.money);
        }
    }

    @Test
    public void testExtend() {
        /**
         * Son have money:0
         * Son have money:4
         * Test father have money:2
         * */
        Father fa = new Son();
        System.out.println("Test father have money:" + fa.money);

        /**
         * Son have money:0
         * Son have money:4
         * Test son have money:4
         * */
        Son son = new Son();
        System.out.println("Test son have money:" + son.money);
    }
}

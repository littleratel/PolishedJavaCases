package cn.bin.innerclass;

public class TestDemo {

    private int a = 0;

    public static void main(String[] args) {
//		mainFun();
    }

    public void mainFun() {
        FunLisenter f = () -> {
            a = 1;
			System.out.println(a);
        };

        new FunLisenter() {
            @Override
            public void fun() {
				a = 1;
                System.out.println(a);
            }
        }.fun();

    }

}
